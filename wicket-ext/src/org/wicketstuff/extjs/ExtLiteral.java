package org.wicketstuff.extjs;

import java.io.Serializable;

public class ExtLiteral implements Serializable {
	
	private String literal;

	public ExtLiteral( String literal ) { 
		this.literal = literal;
	}
	
	@Override
	public String toString() { 
		return literal;
	}

}
