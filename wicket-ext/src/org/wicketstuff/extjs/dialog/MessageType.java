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