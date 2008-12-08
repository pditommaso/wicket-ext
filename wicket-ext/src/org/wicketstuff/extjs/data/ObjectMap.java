package org.wicketstuff.extjs.data;

import java.util.HashMap;

/**
 * Handy class to to replace verbose code like <code>HashMap<String,Object>()</code>
 *  
 * @author Paolo Di Tommaso
 *
 */
public class ObjectMap extends HashMap<String, Object> {

	/**  
	 * Simple constructor 
	 */
	public ObjectMap() { 
		
	}
	
	/**
	 * Initialize the map putting an entry with <code>null</code> value for each attribute in the map
	 * 
	 */
	public ObjectMap( String... attributes ) {
		for( String item : attributes ) { 
			put(item,null);
		}
	}
	
}
