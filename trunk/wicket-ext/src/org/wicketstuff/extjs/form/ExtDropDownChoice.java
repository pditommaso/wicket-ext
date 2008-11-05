package org.wicketstuff.extjs.form;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtComboBoxBehavior;

/**
 * Basic drop down component integrating the Ext class <code>Ext.form.ComboBox</code>
 * 
 */
public class ExtDropDownChoice extends DropDownChoice {

	public ExtDropDownChoice(String id) {
		super(id);
		init();
	}

	public ExtDropDownChoice(String id, IModel model) {
		super(id,model);
		init();
	}
	
	public ExtDropDownChoice(final String id, final List<?> choices) { 
		super(id,choices);
		init();
	}

	public ExtDropDownChoice(final String id, final Object ... choices) { 
		super(id,Arrays.asList(choices));
		init();
	}
	
	/* TODO add same constructors as parent class - paolo */
	
	
	private void init() { 
		add( new ExtComboBoxBehavior() );
	}
	
}
