package org.wicketstuff.extjs.data;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ScriptTagProxy extends ExtClass {

	public ScriptTagProxy(String url) {
		this(new Config("url",url));
	}
	
	public ScriptTagProxy(Config config) { 
		super("Ext.data.ScriptTagProxy", config);
	}

}
