package org.wicketstuff.extjs.behavior;

import org.apache.wicket.Component;
import org.wicketstuff.extjs.Config;

public class ExtPanelBehavior extends ExtComponentBehavior {


	private String wrapperId; 

	{
		defaultOptions.set("collapsible", true);
	}

	public ExtPanelBehavior() {
		super("Ext.Panel");
	}

	public ExtPanelBehavior(Config options) {
		super("Ext.Panel", options);
	}

	
	@Override
	public void onBind() { 
		super.onBind();
		getComponent().setOutputMarkupId(true);
		wrapperId = "wrap:" + getComponent().getMarkupId();
	}
	

	@Override
	protected String getApplyId() { 
		return wrapperId;
	}
	
	@Override
	public void beforeRender(Component component) {
		super.beforeRender(component);
		component.getResponse().write(String.format("<div id='%s'>", wrapperId));
	}
	
	
	@Override
	public void onComponentRendered()  {
		super.onComponentRendered();
		getComponent().getResponse().write("</div>");
	}

	@Override
	protected void onExtConfig( Config options ) { 
		options.set("contentEl", getComponent().getMarkupId() );
		options.set("title", "Hello there!");
		options.set("width", 400);
	}
	
}
