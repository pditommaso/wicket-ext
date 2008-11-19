package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtNumberFieldBehavior;

public class ExtNumberField extends TextField {

	public ExtNumberField(String id) {
		super(id,Long.class);
		init();
	}
	
	public ExtNumberField(String id, Class<?> type) {
		super(id,type);
		init();
	}

	public ExtNumberField(String id, IModel model ) {
		super(id,model,Long.class);
		init();
	}
	
	public ExtNumberField(String id, IModel model, Class<?> type ) {
		super(id,model,type);
		init();
	}

	private void init() {
		add( new ExtNumberFieldBehavior() );
	}

}
