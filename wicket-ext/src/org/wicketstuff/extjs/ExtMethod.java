package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class ExtMethod implements Serializable {
	
	private String method;
	private Collection<Object> params;
	
	public ExtMethod( String method, Object ... params ) { 
		this.method = method;
		this.params = Arrays.asList(params);
	}

	@Override
	public String toString() { 
		StringBuilder result = new StringBuilder(method);
		result.append("(");
		result.append( Ext.serialize(params) );
		result.append(")");
			
		return result.toString();
	}
	
}
