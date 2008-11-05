package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

/**
 * Base behavior to add to Wicket form component the equivalent Ext behavior 
 *  
 * @author Paolo Di Tommaso
 *
 */
public class ExtFormComponentBehavior extends ExtAbstractBehavior {

	private String extClassName;

	private Config defaultOptions = new Config();
	
	
	public ExtFormComponentBehavior( String theFullyQualifiedExtClassName ) { 
		this.extClassName = theFullyQualifiedExtClassName;
	}

	public ExtFormComponentBehavior( String theFullyQualifiedExtClassName, Config options ) { 
		this.extClassName = theFullyQualifiedExtClassName;
		this.defaultOptions = options;
	}
	
	public final String getExtClassName() { 
		return extClassName;
	}


	public Config options() { 
		return defaultOptions;
	}
	
	protected ExtClass create( Config options ) { 
		options.set("renderTo",getComponent().getMarkupId());
		return new ExtClass(extClassName, options);
	}
	
	@Override
	public CharSequence onDomReady() { 
		ExtClass ext = create(new Config(defaultOptions));
		return ext != null ? ext.toString() : null;
		
	}

	public void onRequest() {
		// do nothing by default 
	}
	
}
