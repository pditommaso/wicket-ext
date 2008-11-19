package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Config  implements Serializable {

	Map<String,Object> map = new HashMap<String, Object>();
	
	
	/** 
	 * Default constructor
	 */
	public Config() {
		
	}
	
	public Config( Config copy ) { 
		this.map = new HashMap<String,Object>(copy.map);
	}
	
	public Config( String key, Object value ) { 
		set(key,value);
	}
	
	public Object get( String name ) { 
		return map.get(name);
	}
	
	
	public Config set(String name, Object value ) { 
		map.put(name, value);
		return this;
	}

	public Config put(String name, Object value ) { 
		map.put(name, value);
		return this;
	}
	
	
	public Config putAll( Config config ) { 
		map.putAll(config.map);
		return this;
	}
	
	@Override
	public String toString() { 
		return Ext.serialize(map).toString();
	}

}
