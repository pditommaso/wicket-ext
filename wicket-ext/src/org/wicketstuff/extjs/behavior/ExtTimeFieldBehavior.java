package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTimeFieldBehavior extends ExtFormComponentBehavior{

	public ExtTimeFieldBehavior() { 
		super("Ext.form.TimeField");
	}

	@Override
	public ExtClass create( Config config ) { 
		config.set("applyTo",getComponent().getMarkupId());
		return super.create(config);
	}
	
}
