package org.wicketstuff.extjs;

import java.io.Serializable;

/**
 * Wrapper for JavaScript object 
 * 
 * @author Paolo Di Tommaso
 * 
 * TODO not really required to be removed 
 */

public class ExtArray implements Serializable {

	Object[] items;
	
	public ExtArray( Object ... items ) { 
		this.items = items;
	}
	
	@Override
	public String toString() { 
		StringBuilder result = new StringBuilder();
		result.append("[");
		result.append( Ext.serialize(items) );
		result.append("]");
		return result.toString();
	}
}
