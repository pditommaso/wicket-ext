package org.wicketstuff.extjs;

import java.io.Serializable;


public class ExtClass implements Serializable {

	private String className;
	private Object[] options = {};
	
	public ExtClass( String className ) { 
		this.className = className;
	}

	public ExtClass( String className, Object... options ) { 
		this.className = className;
		this.options = options;
	}
	
	public ExtClass setOptions( Object[] options ) { 
		this.options = options;
		return this;
	}
	
	public Object[] getOptions() { 
		return options;
	}
	
	@Override
	public String toString() { 
		StringBuilder result = new StringBuilder();
		result.append("new ") .append(className) .append("(");
		
		result.append(Ext.serialize(options));

		result.append(")");
		return result.toString();
	}
	
}
