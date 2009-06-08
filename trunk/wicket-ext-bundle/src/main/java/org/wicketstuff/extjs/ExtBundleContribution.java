/*
 *  Copyright (C) 2008 Paolo Di Tommaso
 *  paolo.ditommaso@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.wicketstuff.extjs;
import java.util.WeakHashMap;

import org.apache.wicket.Application;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * Ext 2.2 bundle contributions factory
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtBundleContribution implements ExtContribution {

	private static final long serialVersionUID = 1L;

	transient WeakHashMap<String, ResourceReference> cache;

	IHeaderContributor[] contributions;

	private synchronized ResourceReference get( String resourceName ) {
		ResourceReference result;
		if( cache == null || (result=cache.get(resourceName)) == null ) {
			result = new ResourceReference(ExtBundleContribution.class,resourceName);
			if( cache == null ) { 
				cache = new WeakHashMap<String, ResourceReference>();
			}
			cache.put(resourceName, result);
		}
		return result;
	}

	public ResourceReference EXT_ALL_CSS() {
		return get("resources/css/ext-all.css");
	}

	public ResourceReference EXT_ALL_JS() {
		String resource = Application.get().getDebugSettings().isAjaxDebugModeEnabled()
			            ? "ext-all-debug.js"
			            : "ext-all.js"; 
		return get(resource);
	}

	public ResourceReference EXT_BASE_JS() {
		return get("adapter/ext/ext-base.js");
	}

	public ResourceReference EXT_BLANK() {
		return get("s.gif");
	}

	public IHeaderContributor[] getContributions() {
		if( contributions == null ) {

			IHeaderContributor[] result = new IHeaderContributor[4];
			/*
			 * Ext-all css
			 */
			result[0] = new IHeaderContributor() {
				public void renderHead(IHeaderResponse response) {
					response.renderCSSReference(EXT_ALL_CSS(),"screen");
				}
			} ;

			/*
			 * Ext-base script
			 */
			result[1] = new IHeaderContributor() {
				public void renderHead(IHeaderResponse response) {
					response.renderJavascriptReference(EXT_BASE_JS());
				}
			} ;

			/*
			 * Ext-all javascript
			 */
				result[2] = new IHeaderContributor() {
					public void renderHead(IHeaderResponse response) {
						response.renderJavascriptReference(EXT_ALL_JS());
					}
				} ;

			/*
			 * Blank image resource
			 */

			result[3] = new IHeaderContributor() {
				public void renderHead(IHeaderResponse response) {
					String script = String.format("Ext.BLANK_IMAGE_URL='%s'", RequestCycle.get().urlFor(EXT_BLANK()));
					response.renderJavascript(script, null);
				}
			} ;

			contributions = result;
		}
		return contributions;
	}


}
