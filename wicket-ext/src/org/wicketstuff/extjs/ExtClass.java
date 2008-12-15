package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

/**
 * Wrapper class to handle a Ext class instance
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtClass implements Serializable {

	private String className;
	private Collection<Object> options;
	
	private String varName;
	
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
		if( varName != null ) { 
			return varName;
		}
	
		return newInstance().toString();
	}
	
	public CharSequence newInstance() { 
		StringBuilder result = new StringBuilder();
		result.append("new ") .append(className) .append("(");
		result.append(Ext.serialize(options));
		result.append(")");
		return result;
	}
	
	public CharSequence newInstance( String varName ) { 
		this.varName = varName;
		return String.format("var %s=%s;", varName, newInstance());
	}
	
}