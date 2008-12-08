package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;

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
	protected CharSequence onExtScript(Config config) { 
		StringBuilder result = new StringBuilder()
			.append(varName) .append("=") .append( super.onExtScript(config) ) .append("; ");
			
		return result;
	}
	
	public String getExtInstance() { 
		return varName;
	}
	
}
