package org.wicketstuff.extjs.util;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Response;
import org.apache.wicket.util.string.Strings;

public class XmlRenderer<T> implements Serializable {

	private ObjectMapper<T> mapper;

	private int count;
	
	public XmlRenderer( ObjectMapper<T> mapper ) { 
		this.mapper = mapper;
	}
	
 	
	public void render( T object, Response response, String criteria ) {
		StringBuilder result = new StringBuilder();
		result.append("<item>");
		Map<String,Object> map = mapper.mapObject(object, count++);
		for( Map.Entry<String, Object> entry : map.entrySet() ) { 
			CharSequence key = escape(entry.getKey());
			CharSequence value = escape(entry.getValue());
			result
				.append("<") .append(key) .append(">")
				.append( value )
				.append("</") .append(key) .append(">" );

		}
		result.append("</item>");
		response.write( result );
	}


	public void renderHeader(Response response) {
		response.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		response.write("<dataset>");
		count = 0;
	}
	
	public void renderFooter(Response response) {
		response.write("</dataset>");
	}
	
	private CharSequence escape( Object value ) { 
		//TODO use a better escape function
		return Strings.escapeMarkup(value != null ? value.toString() : "");
	}
}

