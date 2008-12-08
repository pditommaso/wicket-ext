package org.wicketstuff.extjs.data;

/**
 * Define the connection link for Data provider
 * 
 * @author Paolo Di Tommaso
 *
 */

public interface ExtDataLink {

	/**
	 * @return the Ext <code>Store</code> component to manage the data connection
	 */
	Store getStore();
	
	/**
	 * @return defines the url parameter used to query data
	 */
	String getQueryParam();
	
}