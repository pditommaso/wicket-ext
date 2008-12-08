package org.wicketstuff.extjs.data;

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
	
	public ObjectMap mapObject(T object, Integer index) {
		ObjectMap result = new ObjectMap();
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
