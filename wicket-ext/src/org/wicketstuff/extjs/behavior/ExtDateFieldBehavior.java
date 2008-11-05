package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtDateFieldBehavior extends ExtFormComponentBehavior{

	public ExtDateFieldBehavior() { 
		super("Ext.form.DateField");
	}
	
	public ExtClass create( Config config ) { 
		config.set("applyTo",getComponent().getMarkupId());
		return super.create(config);
	}
	
}
