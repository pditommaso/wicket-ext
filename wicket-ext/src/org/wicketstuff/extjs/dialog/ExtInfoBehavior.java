package org.wicketstuff.extjs.dialog;

import org.apache.wicket.model.IModel;


public class ExtInfoBehavior extends ExtMessageBoxBehavior {

	public ExtInfoBehavior() {
		super(MessageType.INFO);
	}


	public ExtInfoBehavior(IModel message) {
		this();
		options().set("msg",message);
	}
	
	public ExtInfoBehavior(String message) {
		this();
		options().set("msg",message);
	}
}
