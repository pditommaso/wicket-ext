package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;


public class ExtClass implements Serializable {

	private String className;
	private Collection<Object> options;
	
	public ExtClass( String className ) { 
		this.className = className;
	}

	public ExtClass( String className, Object... options ) { 
		this.className = className;
		this.options = Arrays.asList(options);
	}
	
	public ExtClass setOptions( Object[] options ) { 
		this.options = Arrays.asList(options);
		return this;
	}
	
	public Object[] getOptions() { 
		return options.toArray() ;
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
