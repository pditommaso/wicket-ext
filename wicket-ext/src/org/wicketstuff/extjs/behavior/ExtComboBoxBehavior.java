package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;

public class ExtComboBoxBehavior extends ExtComponentBehavior {

	{
		defaultOptions.set("typeAhead", true);
		defaultOptions.set("triggerAction","all");
		defaultOptions.set("forceSelection",true);
	}
	
	public ExtComboBoxBehavior() { 
		super("Ext.form.ComboBox");
	}

	public ExtComboBoxBehavior(Config options) { 
		super("Ext.form.ComboBox", options);
	}


	@Override
	protected String getApplyMethod() { 
		return "transform";
	}
	
}
