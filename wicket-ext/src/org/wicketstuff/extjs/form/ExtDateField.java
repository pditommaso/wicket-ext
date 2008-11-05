package org.wicketstuff.extjs.form;

import java.util.Date;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtDateFieldBehavior;

/** 
 * Date field with Ext date chooser widget <code>Ext.form.DateField</code>
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtDateField extends TextField {

	public ExtDateField(String id) {
		super(id,Date.class);
		init();
	}
	
	public ExtDateField( String id, IModel model ) { 
		super(id,model,Date.class);
		init();
	}
	
	private void init() { 
		add( new ExtDateFieldBehavior() );
		add( new SimpleAttributeModifier("size", "12") );
		add( new SimpleAttributeModifier("maxlength", "10") );
	}

}
