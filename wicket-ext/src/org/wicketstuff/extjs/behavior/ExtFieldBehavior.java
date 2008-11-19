package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtFieldBehavior extends ExtComponentBehavior {

	public ExtFieldBehavior(String theFullyQualifiedExtClassName) {
		super(theFullyQualifiedExtClassName);
	}

	public ExtFieldBehavior( String theFullyQualifiedExtClassName, Config options ) { 
		super(theFullyQualifiedExtClassName,options);
	}
	
	@Override
	public ExtClass create( Config config ) { 
		config.set("applyTo",getComponent().getMarkupId());
		return new ExtClass(getExtClassName(), config);
	}
	
}
