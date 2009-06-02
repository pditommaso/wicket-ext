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

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;

/**
 * Defines configuration for various message box types
 *
 * @author Paolo Di Tommaso
 *
 */
public enum MessageType {

	INFO() {
		{
			config = new Config();
			config.set("title", "Info");
			config.set("icon", Ext.literal("Ext.MessageBox.INFO"));
			config.set("buttons", Ext.literal("Ext.MessageBox.OK"));
		}
	} ,

	ALERT() {
		{
			config = new Config();
			config.set("title", "Alert");
			config.set("icon", Ext.literal("Ext.MessageBox.WARNING"));
			config.set("buttons", Ext.literal("Ext.MessageBox.OK"));
		}
	} ,

	CONFIRM() {
		{
			config = new Config();
			config.set("title", "Confirm");
			config.set("icon", Ext.literal("Ext.MessageBox.QUESTION"));
			config.set("buttons", Ext.literal("Ext.MessageBox.YESNO"));
		}
	} ,

	PROMPT() {
		{
			config = new Config();
			config.set("title", "Prompt");
			config.set("prompt", true);
			config.set("buttons", Ext.literal("Ext.MessageBox.OKCANCEL"));
		}
	},

	ERROR() {
		{
			config = new Config();
			config.set("title", "Error");
			config.set("icon", Ext.literal("Ext.MessageBox.ERROR"));
			config.set("buttons", Ext.literal("Ext.MessageBox.OK"));
		}
	} ;

	protected Config config;

	public Config config() { return config; }
}