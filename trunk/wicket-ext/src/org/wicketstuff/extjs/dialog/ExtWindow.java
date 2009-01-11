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

package org.wicketstuff.extjs.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtContainer;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;

/**
 * Wrapper component for <code>Ext.Window</code> class
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtWindow extends ExtContainer {

	Window window;

	private String wrapId;

	public ExtWindow(String id) {
		super(id);
		init();
	}

	public ExtWindow(String id, IModel model) {
		super(id, model);
		init();
	}

	private void init() {
		add(window = new Window(config()));
	}


	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		wrapId = "wrap:" + getMarkupId();
		getResponse().write(String.format("<div id='%s'>", wrapId));
		super.onComponentTagBody(markupStream, openTag);
		getResponse().write("</div>");
	}


	public void show( AjaxRequestTarget target ) {
		target.addComponent(this);	// <-- force component reloading to refresh content
		target.appendJavascript(String.format("%s.show();", window.script()));
	}



	public void setModal( boolean modal ) {
		window.config().set("modal", modal);
	}

	@Deprecated
	public void setBodyStyle(String style) {
		window.config().set("bodyStyle", style);
	}


	/**
	 * Inner window behavior to handle the component
	 *
	 * @author Paolo Di Tommaso
	 *
	 */
	class Window extends ExtComponentBehavior {

		protected Window(Config config) { super("Ext.Window", config);  }

		@Override
		public void onBind() { super.onBind(); getComponent().add(new AttributeAppender("class", new Model("x-hidden"), " " )); }

		@Override
		protected String getApplyMethod() { return "contentEl"; }

		@Override
		protected String getApplyId() { return wrapId; }

		@Override
		protected CharSequence onExtScript( Config config ) { return null; };

		public CharSequence script() { return create(config()).newInstance(); };

	}
}
