package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

/**
 * Base behavior to add to Wicket form component the equivalent Ext behavior 
 *  
 * @author Paolo Di Tommaso
 *
 */
public class ExtComponentBehavior extends ExtAbstractBehavior {

	private String extClassName;
	
	public ExtComponentBehavior( String theFullyQualifiedExtClassName ) { 
		this.extClassName = theFullyQualifiedExtClassName;
	}

	public ExtComponentBehavior( String theFullyQualifiedExtClassName, Config options ) { 
		super(options);
		this.extClassName = theFullyQualifiedExtClassName;
	}
	
	public final String getExtClassName() { 
		return extClassName;
	}


	final protected ExtClass create( Config options ) {
		options.set( getApplyMethod(), getApplyId() );
		return new ExtClass(extClassName, options);
	}
	
	/**
	 * @return the method to be used to apply the Ext component to the html markup element. 
	 * By default it returns <code>applyTo</code>. Can be overridden to return <code>renderTo</code> or component specific method
	 */
	protected String getApplyMethod() { 
		return "applyTo";
	}
	
	/**
	 * @return the element DOM id to which apply/render to Ext component
	 */
	protected String getApplyId() { 
		return getComponent().getMarkupId();
	}
	
	@Override
	final protected CharSequence renderExtScript() {
		/* create a copy of configuration with default options */
		Config config = new Config();
		config.putAll(defaultOptions);
		config.putAll(options());
		onExtConfig(config);
		
		return onExtScript(config);
	}

	/**
	 * Override this method to provide Ext component specific options before the script creation
	 * 
	 * @param config the Ext configuration object 
	 */
	protected void onExtConfig( Config config ) { 
	}


	/**
	 * Override this method to manipulate the generated JavaScript code for the Ext component.
	 * 
	 * @param script the JavaScript code
	 * @return the modified script. If the return <code>null</code> the script will not rendered
	 */
	protected CharSequence onExtScript(Config config) {
		return create(config).newInstance();
	}

	
}
