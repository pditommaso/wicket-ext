package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.Map;

/**
 * Wrapper class to handle Ext <code>Ext.Ajax.request</code> method
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtAjax implements Serializable {
	
	public static String request( String url ) { 
		Config options = new Config() .set("url", url);
		return request( options );
	}

	
	public static String request( String url, Map<String,Object> params ) { 
		Config options = new Config() 
			.set("url", url)
			.set("params", params);
		return request( options );
	}
	
	
	
	public static String request( Config options ) { 
		return new ExtMethod( "Ext.Ajax.request", options ).toString(); 
	}

}
