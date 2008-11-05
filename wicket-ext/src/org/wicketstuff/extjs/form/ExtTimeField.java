package org.wicketstuff.extjs.form;

import java.util.Date;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtTimeFieldBehavior;

/**
 * Date field with Ext date chooser widget <code>Ext.form.TimeField</code>
 * 
 * @author Paolo Di Tommaso
 */
public class ExtTimeField extends TextField {

	public ExtTimeField(String id) {
		super(id,Date.class);
		init();
	}
	
	public ExtTimeField( String id, IModel model ) { 
		super(id,model,Date.class);
		init();
	}
	
	private void init() { 
		add( new ExtTimeFieldBehavior() );
	}

}
