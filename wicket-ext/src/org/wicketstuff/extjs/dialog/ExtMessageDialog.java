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
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;


/**
 * Integrate the <code>Ext.MessageBox</code> dialogs with Wicket
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtMessageDialog extends WebMarkupContainer {

	public static final String BTN_YES = "yes";
	public static final String BTN_NO = "no";
	public static final String BTN_OK = "ok";
	public static final String BTN_CANCEL = "cancel";

	private ExtMessageBoxBehavior handler;

	public ExtMessageDialog(String id) {
		super(id);
		init();
	}

	private void init() {
		Ext.addContribution(this);

		handler = new ExtMessageBoxBehavior() {
			@Override
			public void onClick(AjaxRequestTarget target, String button, String text) {
				ExtMessageDialog.this.onClick(button,text);
			}
		} ;
		add(handler);
	}


	/**
	 * Callback handler on message box button click. Ovveride this method to intercept the message button events
	 *
	 * @param pressedButton the button pressed on the dialog
	 * @param text the entered text (only for prompt dialog)
	 */
	protected void onClick( final String pressedButton, final String text ) {

	}

	/**
	 * Displays a <i>Confirm</i> dialog
	 *
	 * @param target the current ajax request target
	 * @param title the dialog string title
	 * @param message the dialog content message
	 */
	public void confim( AjaxRequestTarget target, String title, String message ) {
		Config config = new Config( MessageType.CONFIRM.config() );
		config.put("title",title);
		config.put("msg",message);
		show(target,config);
	}

	/**
	 * Display a <i>Prompt</i> Dialog
	 *
	 * @param target the current ajax request target
	 * @param title the prompt dialog title string
	 * @param message the prompt dialog message string
	 */
	public void prompt( AjaxRequestTarget target, String title, String message  ) {
		prompt(target,title,message,"", false);
	}

	/**
	 * Display a <i>Prompt</i> Dialog
	 *
	 * @param target the current ajax request target
	 * @param title the prompt dialog title string
	 * @param message the prompt dialog message string
	 * @param defValue the default value displayed in the input field
	 * @param isMultiline when <code>true</code> dialog will be display a multiline inout area
	 */
	public void prompt( AjaxRequestTarget target, String title, String message, String defValue, boolean isMultiline  ) {
		Config config = new Config( MessageType.PROMPT.config() );
		config.put("title",title);
		config.put("msg",message);
		config.put("multiline", isMultiline);
		config.put("value", defValue);
		show(target,config);
	}

	/**
	 * Display an <i>Alert</i> Dialog
	 *
	 * @param target the current ajax request target
	 * @param title the altet dialog title string
	 * @param message the alert dialog message string
	 */
	public void alert( AjaxRequestTarget target, String title, String message  ) {
		Config config = new Config( MessageType.ALERT.config() );
		config.put("title",title);
		config.put("msg",message);
		show(target,config);
	}

	/**
	 * Display a configurable dialog message
	 *
	 * @param target the current ajax request target
	 * @param options configuration object, see <i>Ext</i> documentation for references
	 */
	public void show( AjaxRequestTarget target, Config config ) {
		String script = handler.dialogScript(config);
		target.appendJavascript(script);
	}

}
