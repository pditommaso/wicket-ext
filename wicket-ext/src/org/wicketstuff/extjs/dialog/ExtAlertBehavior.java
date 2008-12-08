package org.wicketstuff.extjs.dialog;

import org.apache.wicket.model.IModel;


public class ExtAlertBehavior extends ExtMessageBoxBehavior {

	public ExtAlertBehavior() {
		super(MessageType.ALERT);
	}


	public ExtAlertBehavior(IModel message) {
		this();
		options().set("msg",message);
	}
	
	public ExtAlertBehavior(String message) {
		this();
		options().set("msg",message);
	}
}
