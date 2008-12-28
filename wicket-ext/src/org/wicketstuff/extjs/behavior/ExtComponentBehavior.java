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

package org.wicketstuff.extjs.behavior;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Application;
import org.apache.wicket.markup.ComponentTag;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;

/**
 * Base behavior to add to Wicket form component the equivalent Ext behavior
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtComponentBehavior extends ExtAbstractBehavior {

	private String extClassName;

	private boolean isConfigured = false;

	public ExtComponentBehavior( String theFullyQualifiedExtClassName ) {
		extClassName = theFullyQualifiedExtClassName;
	}

	public ExtComponentBehavior( String theFullyQualifiedExtClassName, Config options ) {
		super(options);
		extClassName = theFullyQualifiedExtClassName;
	}

	public final String getExtClassName() {
		return extClassName;
	}


	final protected ExtClass create( Config options ) {
		options.set( getApplyMethod(), getApplyId() );
		return new ExtClass(extClassName, options);
	}

	/**
	 * @return the method to be used to apply the Ext component to the html markup element.
	 * By default it returns <code>applyTo</code>. Can be overridden to return <code>renderTo</code> or component specific method
	 */
	protected String getApplyMethod() {
		return "applyTo";
	}

	/**
	 * @return the element DOM id to which apply/render to Ext component
	 */
	protected String getApplyId() {
		return getComponent().getMarkupId();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void onComponentTag(final ComponentTag tag) {
		super.onComponentTag(tag);
		if( isConfigured ) {
			return;
		}

		List<String> attributes = new ArrayList<String>(tag.getAttributes().keySet());
		for( String attr : attributes ) {
			if( attr.startsWith("ext:") ) {
				Object val = tag.getAttributes().get(attr);
				config().putIfNotExists( attr.substring(4), Ext.parseValue( val != null ? val.toString() : null ));

				if( Application.get().getMarkupSettings().getStripWicketTags() ) {
					// remove the attribute from the markup
					tag.remove(attr);
				}
			}
		}
		// set the 'isConfigured' flag to true
		isConfigured = true;
	}

	@Override
	final protected CharSequence renderExtScript() {

		/* create a copy of configuration with default options */
		Config config = new Config();
		config.putAll(config());
		onExtConfig(config);

		return onExtScript(config);
	}

	/**
	 * Override this method to provide Ext component specific options before the script creation
	 *
	 * @param config the Ext configuration object
	 */
	protected void onExtConfig( Config config ) {
	}


	/**
	 * Override this method to manipulate the generated JavaScript code for the Ext component.
	 *
	 * @param script the JavaScript code
	 * @return the modified script. If the return <code>null</code> the script will not rendered
	 */
	protected CharSequence onExtScript(Config config) {
		return create(config).newInstance();
	}


}
