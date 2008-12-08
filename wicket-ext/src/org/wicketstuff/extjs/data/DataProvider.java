package org.wicketstuff.extjs.data;

import java.util.Iterator;

/**
 * Defines a generic data-link interface to used as data provider by Ext components
 * 
 * @author Paolo Di Tommaso
 */

public interface DataProvider<T> extends ObjectMapper<T> {

	Iterator<T> iterator( String filter, String direction, Integer start, Integer count );
	
	Long totalRecords();
}
