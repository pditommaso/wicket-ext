package org.wicketstuff.extjs.grid;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.data.ExtDataLink;

public class ExtPagingToolbar extends ExtClass {

	
	private static Config defaultOptions = new Config();
	
	{
		defaultOptions.put("pageSize", 25);
		defaultOptions.put("displayInfo", true);
		defaultOptions.put("displayMsg","Displaying topics {0} - {1} of {2}");
		defaultOptions.put("emptyMsg", "No topics to display");
	}
	
	private ExtDataLink link;
	private Config config;
	
	public ExtPagingToolbar(ExtDataLink link) {
		this(link, new Config());
	}

	public ExtPagingToolbar(ExtDataLink link, Config config) {
		super("Ext.PagingToolbar",config);
		this.link = link;
		this.config = config;
	}

	@Override
	public CharSequence newInstance() { 
		config.putNotExisting(defaultOptions);
		config.put("store", link.getStore() );
		return super.newInstance();
	}
	
	
	public void setPageSize( int pageSize ) { 
		config.set("pageSize", pageSize);
	}
	
}
