package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;


/** 
 * 
 * Enable regexp masking on textfield
 *
 * @author Matteo Fiandesio
 *
 */


public class ExtMaskedTextBehaviour extends ExtComponentBehavior {
	
	String mask;
	
	public ExtMaskedTextBehaviour(String regExpMask){
		super("Ext.form.TextField");
		this.mask = regExpMask;
	}
	
	
	@Override
	protected void onExtConfig( Config options ) { 
		options.set("maskRe",new ExtClass("RegExp",new String(mask)));
	}
	
}
