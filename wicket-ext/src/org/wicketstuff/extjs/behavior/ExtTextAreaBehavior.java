package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTextAreaBehavior extends ExtFormComponentBehavior {


	public ExtTextAreaBehavior() { 
		super("Ext.form.TextArea");
	}

	@Override
	public ExtClass create( Config config ) { 
		config.set("applyTo",getComponent().getMarkupId());
		return super.create(config);
	}
	
}
