package org.wicketstuff.extjs.tree;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTreeLoader extends ExtClass {

	
	private static final long serialVersionUID = 1L;
	private static Config defaultOptions = new Config();
	
	{
		defaultOptions.put("draggable", false);
	}
	
	private Config config;
	
	public ExtTreeLoader(String url) {
		this(url,new Config());
	}

	public ExtTreeLoader(String url, Config config) {
		super("Ext.tree.TreeLoader",config);
		config.put("url", url);
		this.config = config;
	}

	@Override
	public CharSequence newInstance() { 
		config.putNotExisting(defaultOptions);
		return super.newInstance();
	}	
}
