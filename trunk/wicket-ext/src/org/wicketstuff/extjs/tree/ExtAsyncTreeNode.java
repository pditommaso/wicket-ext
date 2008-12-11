package org.wicketstuff.extjs.tree;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtAsyncTreeNode extends ExtClass {

	
	private static final long serialVersionUID = 1L;
	public static final String ROOT_ID = "@root@";
	private static Config defaultOptions = new Config();
	
	{
		defaultOptions.put("draggable", false);
	}
	
	private Config config;
	
	public ExtAsyncTreeNode(String text, String id) {
		this(text,id,new Config());
	}

	public ExtAsyncTreeNode(String text, String id, Config config) {
		super("Ext.tree.AsyncTreeNode",config);
		config.put("text", text);
		config.put("id", id);
		this.config = config;
	}

	@Override
	public CharSequence newInstance() { 
		config.putNotExisting(defaultOptions);
		return super.newInstance();
	}	
}
