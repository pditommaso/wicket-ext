package org.wicketstuff.extjs.dialog;

import org.apache.wicket.model.IModel;


public class ExtErrorBehavior extends ExtMessageBoxBehavior {

	public ExtErrorBehavior() {
		super(MessageType.ERROR);
	}


	public ExtErrorBehavior(IModel message) {
		this();
		options().set("msg",message);
	}
	
	public ExtErrorBehavior(String message) {
		this();
		options().set("msg",message);
	}
}
