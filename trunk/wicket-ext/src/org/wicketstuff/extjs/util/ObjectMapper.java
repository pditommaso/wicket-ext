package org.wicketstuff.extjs.util;

import java.io.Serializable;
import java.util.Map;

/**
 * Maps an object attribute to its values 
 * 
 * @author Paolo Di Tommaso
 *
 */
public interface ObjectMapper<T> extends Serializable {

	Map<String,Object> mapObject( T object, int index  );
	
}
