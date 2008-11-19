package org.wicketstuff.extjs.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UrlBuilder implements Serializable {

	CharSequence url;
	Map<String,Object> params = new HashMap<String, Object>();
	
	public UrlBuilder( String url ) { 
		this.url = url;
	}
	
	public UrlBuilder( CharSequence url ) { 
		this.url = url;
	}
	
	
	public UrlBuilder append( String name, String value ) { 
		params.put(name,value);
		return this;
	}
	
	public UrlBuilder append( Map<String,Object> params ) { 
		this.params.putAll(params);
		return this;
	}
	
	@Override
	public String toString() { 
		StringBuilder builder = new StringBuilder()
			.append("'") .append(url) .append("'");
		
		if( params != null ) { 
			for( Map.Entry<String, Object> item: params.entrySet() ) { 
				builder 
				.append("+'&")
				.append(item.getKey())
				.append("='+")
				.append("escape(")
				.append( item.getValue() )
				.append(")");
			}
		}
		return builder.toString();
	}
	
	
}
