package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

/**
 * Wrapper class to handle an Ext <code>Ext.Window</code>
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtWindowBehavior extends ExtComponentBehavior {

	private String varName;
	
	public ExtWindowBehavior( ) { 
		super("Ext.Window");
	}
	
	public ExtWindowBehavior( Config config ) { 
		super("Ext.Window", config);
	}
	
	@Override
	public void onBind() { 
		super.onBind();
		varName = "ext_" + getComponent().getMarkupId(); 
	}
	
	@Override
	protected ExtClass create( Config options ) { 
		options.set("applyTo",getComponent().getMarkupId());
		return new ExtClass(getExtClassName(), options);
	}
	
	@Override
	protected CharSequence onDomReady() { 
		CharSequence script = super.onDomReady();
		StringBuilder result = new StringBuilder()
			.append(varName) .append("=") .append(script) .append("; ");
			
		return result;
	}
	
	public String getExtInstance() { 
		return varName;
	}
	
}
