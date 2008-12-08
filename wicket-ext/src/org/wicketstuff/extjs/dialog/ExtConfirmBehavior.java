package org.wicketstuff.extjs.dialog;

import org.apache.wicket.model.IModel;


public class ExtConfirmBehavior extends ExtMessageBoxBehavior {

	public ExtConfirmBehavior() {
		super(MessageType.CONFIRM);
	}


	public ExtConfirmBehavior(IModel message) {
		this();
		options().set("msg",message);
	}
	
	public ExtConfirmBehavior(String message) {
		this();
		options().set("msg",message);
	}
}
