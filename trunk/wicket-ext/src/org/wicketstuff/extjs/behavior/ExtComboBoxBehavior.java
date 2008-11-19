package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtComboBoxBehavior extends ExtComponentBehavior {

	
	public ExtComboBoxBehavior() { 
		super("Ext.form.ComboBox");
	}

	public ExtComboBoxBehavior(Config options) { 
		super("Ext.form.ComboBox", options);
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
