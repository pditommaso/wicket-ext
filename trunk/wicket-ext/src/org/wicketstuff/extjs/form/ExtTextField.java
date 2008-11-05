package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtTextFieldBehavior;

/**
 * Generic Text field integrating the Ext <code>Ext.form.TextField</code>
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtTextField extends TextField {

	public ExtTextField(String id) {
		super(id);
		init();
	}
	
	public ExtTextField( String id, IModel model ) { 
		super(id,model);
		init();
	}
	
	private void init() { 
		add( new ExtTextFieldBehavior() );
	}

}
