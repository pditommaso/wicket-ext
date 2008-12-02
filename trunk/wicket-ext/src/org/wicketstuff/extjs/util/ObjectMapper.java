package org.wicketstuff.extjs.util;

import java.io.Serializable;
import java.util.Map;

/**
 * Translate an object instance to equivalent an {@link Map} representation  
 * 
 * @author Paolo Di Tommaso
 *
 */
public interface ObjectMapper<T> extends Serializable {

	/**
	 * Translate an object to an equivalent map.
	 * <p>
	 * Please note: when <code>object</code> parameter is <code>null</code> a map instance representing the expected object structure must be returned 
	 * 
	 * @param object the instance to translate
	 * @param index the index count if invoked in an iteration
	 * @return the mapped object 
	 */
	Map<String,Object> mapObject( T object, int index  );
	
}
