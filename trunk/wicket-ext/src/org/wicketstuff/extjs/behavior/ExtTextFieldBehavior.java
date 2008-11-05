package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTextFieldBehavior extends ExtFormComponentBehavior {

	public ExtTextFieldBehavior() {
		super("Ext.form.TextField");
	}

	
	@Override
	public ExtClass create( Config config ) { 
		config.set("applyTo",getComponent().getMarkupId());
		return super.create(config);
	}
	
}
