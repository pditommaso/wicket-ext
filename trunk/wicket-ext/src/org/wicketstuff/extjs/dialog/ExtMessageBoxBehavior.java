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
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ExtMethod;
import org.wicketstuff.extjs.behavior.ExtAbstractBehavior;
import org.wicketstuff.extjs.util.WicketCallBuilder;

/**
 * Behavior that renders an Ext dialog box on a component event
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtMessageBoxBehavior extends ExtAbstractBehavior {

	public ExtMessageBoxBehavior() {
	}

	public ExtMessageBoxBehavior( MessageType type ) {
		super(type.config());
	}

	public ExtMessageBoxBehavior( Config config ) {
		super(config);
	}

	@Override
	final public void onEvent(AjaxRequestTarget target ) {
		String pressedButton = getComponent().getRequest().getParameter("btn");
		String text = getComponent().getRequest().getParameter("txt");
		onClick( target, pressedButton, text );
	}

	/**
	 * Public callback method. Override this method to intercet the Mesasge Dialog button click event
	 *
	 * @param pressedButton
	 */
	public void onClick(final AjaxRequestTarget target, final String pressedButton, final String text) {

	}

	private ExtFunction callbackFn() {
		WicketCallBuilder call = new WicketCallBuilder(this.getCallbackUrl());
		call.append("btn", "btn");
		call.append("txt", "txt");
		return new ExtFunction( "btn,txt", call.toString() );
	}


	String dialogScript(Config options) {
		options.set("fn", callbackFn());
		ExtMethod dialog = new ExtMethod("Ext.MessageBox.show", options );
		return dialog.toString();
	}

	@Override
	final protected CharSequence renderExtScript() {
		/*
		 * attach the behavior to client side DOM object
		 */
		ExtFunction function = new ExtFunction( dialogScript(config()) );

		String id = getComponent().getMarkupId();
		return String.format("Ext.get('%s').on('click', %s )", id, function.toString() );
	};

}
