package org.wicketstuff.extjs;

import java.io.Serializable;

import org.apache.wicket.util.string.Strings;

/**
 * JavaScript anonymous function wrapper 
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtFunction implements Serializable {
	
	private String body;
	private String arguments;

	public ExtFunction(String body) { 
		this.body = body;
	}
	
	public ExtFunction(String arguments, String body ) { 
		this.arguments = arguments;
		this.body = body;
	}

	@Override
	public String toString() { 
		StringBuilder result = new StringBuilder("function");
		result.append("(");
		if( !Strings.isEmpty(arguments) ) { 
			result.append(arguments);
		}
		result.append(")");
		
		result.append("{ ") .append(body) .append(" }");
		return result.toString();
	}
}
