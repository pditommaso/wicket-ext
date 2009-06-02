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

import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;

/**
 * Wrapper class for <code>Ext.ToolTip</code>
 *
 * @author vmoreau
 * @author Paolo Di Tommaso
 *
 */
public class ExtToolTipBehavior extends ExtComponentBehavior {

	private static final long serialVersionUID = 1L;

	/**
	 * @param text the string value to be displayed in the tooltip
	 */
	public ExtToolTipBehavior(String text) {
		super("Ext.ToolTip");
		config().set("html", text);
	}

	/**
	 * Create the tooltip using a Wicket model to specify the tip text.
	 *
	 * @param text the model which object will be displayed in the tooltip as text
	 */
	public ExtToolTipBehavior(IModel text) {
		super("Ext.ToolTip");
		config().set("html", text);
	}

	/**
	 * Create the tooltip using a Wicket model to specify the tip text.
	 *
	 * @param options the Ext configuration object
	 * @param text the model which object will be displayed in the tooltip as text
	 */
	public ExtToolTipBehavior(Config options,IModel text) {
		super("Ext.ToolTip", options);
		config().set("html", text);
	}

	@Override
	protected String getApplyMethod() {
		return "target";
	}

	public void setTitle(String title) {
		config().set("title", title);
	}

	public void setTitle(IModel title) {
		config().set("title", title);
	}


	public void setAutoHide(boolean autoHide) {
		config().set("autoHide", autoHide);
		config().set("closable", !autoHide);
	}

	public void setDraggable(boolean draggable) {
		config().set("draggable", draggable);
	}

	public void setTrackMouse(boolean trackMouse) {
		config().set("trackMouse", trackMouse);
	}


}

