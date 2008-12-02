package org.wicketstuff.extjs.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.util.lang.PropertyResolver;

/**
 * Basinc implentation for {@link ObjectMapper}
 * 
 * @author Paolo Di Tommaso
 *
 * @param <T>
 */
public class SimpleObjectMapper<T> implements ObjectMapper<T> {

	final String[] attributes;
	
	/**
	 * Create the mapper for the specified object attributes 
	 * @param attributes the list on attributes name 
	 */
	public SimpleObjectMapper( String ... attributes ) { 
		this.attributes = attributes;
	}
	
	public Map<String, Object> mapObject(T object, int index) {
		Map<String,Object> result = new HashMap<String, Object>();
		for( String name : attributes ) { 
			if( object != null ) { 
				result.put( name, PropertyResolver.getValue(name, object) );
			}
			else { 
				result.put(name, null);
			}
		}
		
		return result;
	}

}
