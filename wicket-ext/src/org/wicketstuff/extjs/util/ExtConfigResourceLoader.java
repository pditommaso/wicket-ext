/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketstuff.extjs.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.resource.IPropertiesFactory;
import org.apache.wicket.resource.Properties;
import org.apache.wicket.util.resource.locator.ResourceNameIterator;
import org.apache.wicket.util.string.AppendingStringBuffer;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;

/**
 * Load the configuration object (expressed in the JSON format) for a Ext class from configuration properties files
 *
 * NOT PUBLIC API - DON'T USE IT
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtConfigResourceLoader
{

	private static final WeakHashMap<String, Config> cacheFirst = new WeakHashMap<String, Config>();

	private static final WeakHashMap<Integer, Properties> cacheSecond = new WeakHashMap<Integer, Properties>();

	private IPropertiesFactory propertiesFactory;

	public ExtConfigResourceLoader() {
		// Load the properties associated with the path
		propertiesFactory = Application.get() .getResourceSettings() .getPropertiesFactory();
	}

	/**
	 * Find out the configuration object for specified Ext class name and named component
	 * @param component the wicket component instance
	 * @param className the ext class name used as key in the property file for <i>component</i>
	 * @return the object map with the retrieved configuration options
	 */
	public Config loadConfigResource( final Component component, final String className ) {

		String cacheKey = getCacheKey(className, component);
		if( cacheFirst.containsKey(cacheKey) ) {
			return new Config(cacheFirst.get(cacheKey)); // <-- always return the object copy
		}

		Config config = new Config();
		loadStringResource(component, className, config);
		cacheFirst.put(cacheKey, config);

		return new Config(config);
	}

	/**
	 * Get the string resource for the given combination of class, key, locale and style. The
	 * information is obtained from a resource bundle associated with the provided Class (or one of
	 * its super classes).
	 *
	 * @param clazz
	 *            The Class to find resources to be loaded
	 * @param keyPrefix
	 *            The key to obtain the string for
	 * @param locale
	 *            The locale identifying the resource set to select the strings from
	 * @param style
	 *            The (optional) style identifying the resource set to select the strings from (see
	 *            {@link org.apache.wicket.Session})
	 * @return The string resource value or null if resource not found
	 */
	protected void loadStringResource(Class clazz, final String keyPrefix, final Locale locale, final String style, final Config config )
	{
		if (clazz == null)
		{
			return;
		}

		while (true)
		{
			// Create the base path
			String path = clazz.getName().replace('.', '/');

			// Iterator over all the combinations
			ResourceNameIterator iter = new ResourceNameIterator(path, style, locale, "properties,xml");
			while (iter.hasNext())
			{
				String newPath = (String)iter.next();

				final Properties props = loadProps(clazz, newPath);
				if (props != null)
				{
					matchProps(props,keyPrefix,config);
				}
			}

			// Didn't find the key yet, continue searching if possible
			if (isStopResourceSearch(clazz))
			{
				break;
			}

			// Move to the next superclass
			clazz = clazz.getSuperclass();

			if (clazz == null)
			{
				// nothing more to search, done
				break;
			}
		}
	}

	private Properties loadProps( Class clazz, String newPath ) {
		int key = 7;
		key = 31 * clazz.hashCode();
		key = 31 * newPath.hashCode();
		// check if properties has been cached
		if( cacheSecond.containsKey(key) ) {
			return cacheSecond.get(key);
		}
		// otherwise load from file system and store in cache
		Properties result = propertiesFactory.load(clazz, newPath);
		cacheSecond.put(key, result);
		return result;
	}

	private void matchProps( Properties props, String keyPrefix, Config config ) {
		// Lookup the value
		Iterator i = props.getAll().keySet().iterator();
		while( i.hasNext() ) {
			String name = (String) i.next();
			if( name.startsWith(keyPrefix + ".") ) {
				String property = name.substring(keyPrefix.length()+1);
				config.putIfNotExists(property, Ext.parseValue(props.getString(name)));
			}
		}
	}

	protected void loadStringResource(final Component component, final String key, final Config config )
	{
		if (component == null)
		{
			return;
		}

		Locale locale = component.getLocale();
		String style = component.getStyle();

		// The key prefix is equal to the component path relative to the
		// current component on the top of the stack.
		String prefix = Strings.replaceAll(component.getPageRelativePath(), ":", ".").toString();

		// The reason why we need to create that stack is because we need to
		// walk it downwards starting with Page down to the Component
		List searchStack = getComponentStack(component);

		// Walk the component hierarchy down from page to the component
		for (int i = searchStack.size() - 1; i >= 0; i--)
		{
			Class clazz = (Class)searchStack.get(i);

			// First, try the fully qualified resource name relative to the
			// component on the path from page down.
			if ((prefix != null) && (prefix.length() > 0))
			{
				loadStringResource(clazz, prefix + '.' + key, locale, style, config);
				prefix = Strings.afterFirst(prefix, '.');
			}

			if( i == 0 && component.getClass().equals(clazz)) {
				/*
				 * Before the last component that is supposed to contains the components default we try in the application configuration file
				 */
				loadStringResource( Application.get().getClass(), key, locale, style, config);
			}

			loadStringResource(clazz, key, locale, style, config);
		}

	}

	/**
	 * Traverse the component hierarchy up to the Page and add each component class to the list
	 * (stack) returned
	 *
	 * @param component
	 *            The component to evaluate
	 * @return The stack of classes
	 */
	private List getComponentStack(final Component component)
	{
		// Build the search stack
		final List searchStack = new ArrayList();
		searchStack.add(component.getClass());

		if (!(component instanceof Page))
		{
			// Add all the component on the way to the Page
			MarkupContainer container = component.getParent();
			while (container != null)
			{
				searchStack.add(container.getClass());
				if (container instanceof Page)
				{
					break;
				}

				container = container.getParent();
			}
		}
		return searchStack;
	}

	/**
	 * Check the supplied class to see if it is one that we shouldn't bother further searches up the
	 * class hierarchy for properties.
	 *
	 * @param clazz
	 *            The class to check
	 * @return Whether to stop the search
	 */
	protected boolean isStopResourceSearch(final Class clazz)
	{
		if (clazz == null || clazz.equals(Object.class) || clazz.equals(Application.class))
		{
			return true;
		}

		// Stop at all html markup base classes
		if (clazz.equals(WebPage.class) || clazz.equals(WebMarkupContainer.class) ||
			clazz.equals(WebComponent.class))
		{
			return true;
		}

		// Stop at all wicket base classes
		return clazz.equals(Page.class) || clazz.equals(MarkupContainer.class) ||
			clazz.equals(Component.class);
	}

	protected String getCacheKey(final String key, final Component component)
	{
		String cacheKey = key;
		if (component != null)
		{
			AppendingStringBuffer buffer = new AppendingStringBuffer(200);
			buffer.append(key);

			Component cursor = component;
			while (cursor != null)
			{
				buffer.append("-").append(cursor.getClass().hashCode());

				if (cursor instanceof Page)
				{
					break;
				}

				if (cursor.getParent() != null && !(cursor.getParent() instanceof AbstractRepeater))
				{
					/*
					 * only append component id if parent is not a repeater because
					 *
					 * (a) these ids are irrelevant when generating resource cache keys
					 *
					 * (b) they cause a lot of redundant keys to be generated
					 */
					buffer.append(":").append(cursor.getId());
				}
				cursor = cursor.getParent();

			}

			buffer.append("-").append(component.getLocale());
			buffer.append("-").append(component.getStyle());
			// TODO 1.4 look if we want to properly separate getstyle/getvariation
			// for now getvariation() is rolled up into getstyle()
			// buffer.append("-").append(component.getVariation());
			cacheKey = buffer.toString();
		}
		return cacheKey;
	}

}
