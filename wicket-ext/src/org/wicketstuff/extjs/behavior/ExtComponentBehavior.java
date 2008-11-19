package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.form.ExtComponent;

/**
 * Base behavior to add to Wicket form component the equivalent Ext behavior 
 *  
 * @author Paolo Di Tommaso
 *
 */
public class ExtComponentBehavior extends ExtAbstractBehavior {

	private String extClassName;

	private Config defaultOptions = new Config();
	
	
	public ExtComponentBehavior( String theFullyQualifiedExtClassName ) { 
		this.extClassName = theFullyQualifiedExtClassName;
	}

	public ExtComponentBehavior( String theFullyQualifiedExtClassName, Config options ) { 
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
		Config options = new Config(defaultOptions);
		if( getComponent() instanceof ExtComponent ) { 
			ExtComponent ext = (ExtComponent) getComponent();
			options.putAll(ext.getOptions());
		}
		ExtClass ext = create(options);
		return ext != null ? ext.toString() : null;
		
	}

	public void onRequest() {
		// do nothing by default 
	}
	
}
