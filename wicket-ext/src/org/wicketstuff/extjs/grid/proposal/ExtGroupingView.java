package org.wicketstuff.extjs.grid.proposal;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;

public class ExtGroupingView extends ExtClass {
	
	private static final long serialVersionUID = 1L;
	private static Config defaultOptions = new Config();
	
	{
		defaultOptions.put("forceFit", true);
		defaultOptions.put("groupTextTpl", Ext.literal("'{text} ({[values.rs.length]} {[values.rs.length > 1 ? \"Items\" : \"Item\"]})'"));
	}
	
	private Config config;
	
	public ExtGroupingView() {
		this(new Config());
	}

	public ExtGroupingView(Config config) {
		super("Ext.grid.GroupingView",config);
		this.config = config;
	}

	@Override
	public CharSequence newInstance() { 
		config.putNotExisting(defaultOptions);
		return super.newInstance();
	}
	
}
