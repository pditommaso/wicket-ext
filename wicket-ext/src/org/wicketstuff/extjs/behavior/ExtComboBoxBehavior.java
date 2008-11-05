package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

@SuppressWarnings("serial")
public class ExtComboBoxBehavior extends ExtFormComponentBehavior {

	
	public ExtComboBoxBehavior() { 
		super("Ext.form.ComboBox");
	}

	@Override
	protected ExtClass create(Config options) {
		
		Config config = new Config()
			.set("typeAhead", true)
			.set("triggerAction","all")
			.set("transform", getComponent().getMarkupId())
			.set("forceSelection",true);
		
		return new ExtClass( getExtClassName(), config );
	}
}
