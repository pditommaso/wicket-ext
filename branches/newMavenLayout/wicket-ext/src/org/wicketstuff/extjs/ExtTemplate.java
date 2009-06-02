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

package org.wicketstuff.extjs;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * Component for declarative templating in markup.
 * <p>
 * Ext template should be declared inside a hidden (<code>display:none</code>) <code>&lt;textarea&gt;</code>
 * markup element. If not this component will replace the declared markup with a textarea
 */
public class ExtTemplate extends WebMarkupContainer implements ITemplate {

	String itemSelector = "div.search-item";

	public ExtTemplate(String id) {
		super(id);
		init();
	}

	public ExtTemplate(String id, String itemSelector) {
		super(id);
		this.itemSelector = itemSelector;
		init();
	}

	private void init() {
		setOutputMarkupId(true);
		// hide the container
		add(new SimpleAttributeModifier("style", "display:none"));
	}

	/**
	 * @return
	 * 		the item selector string for the template markup
	 */
	public String getItemSelector() {
		return itemSelector;
	}

	public void setItemSelector( String itemSelector ) {
		this.itemSelector = itemSelector;
	}

	public XTemplate getXTemplate() {
		return XTemplate.from( getMarkupId() );
	}

	/**
	 * Make sure that the template is declared inside a <code>&lt;textarea&gt;</code> element.
	 * <p>
	 * This is required by Internet Explorer
	 */
	@Override
	protected void onComponentTag(final ComponentTag tag) {
		if( !tag.getName().equalsIgnoreCase("textarea") ) {
			tag.setName("textarea");
		}
		super.onComponentTag(tag);
	}

}
