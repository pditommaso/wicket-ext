package org.wicketstuff.extjs.grid.proposal;


/**
 * Define the connection link for Data provider
 * 
 * @author Paolo Di Tommaso
 *
 */

public interface ExtDataGroupingLink {

	/**
	 * @return the Ext <code>Store</code> component to manage the data connection
	 */
	ExtGroupingStore getStore();
	
	/**
	 * @return defines the url parameter used to query data
	 */
	String getQueryParam();
	
}