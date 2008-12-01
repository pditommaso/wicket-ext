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


public class ExtMaskedTextBehaviour extends ExtFieldBehavior {
	
	String mask;
	
	public ExtMaskedTextBehaviour(String regExpMask){
		super("Ext.form.TextField");
		this.mask = regExpMask;
	}
	
	
	@Override
	public ExtClass create( Config options ) { 
		options
			.set("maskRe",new ExtClass("RegExp",new String(mask)));
		
		return super.create(options);
	}
	
}