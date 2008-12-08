package org.wicketstuff.extjs.dialog;

import org.apache.wicket.model.IModel;


public class ExtPromptBehavior extends ExtMessageBoxBehavior {

	public ExtPromptBehavior() {
		super(MessageType.PROMPT);
	}


	public ExtPromptBehavior(IModel message) {
		this();
		options().set("msg",message);
	}
	
	public ExtPromptBehavior(String message) {
		this();
		options().set("msg",message);
	}
}
