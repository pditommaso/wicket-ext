package org.wicketstuff.extjs.behavior;

import org.apache.wicket.Component;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtPanelBehavior extends ExtComponentBehavior {


	private String wrapperId; 


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
	protected ExtClass create( Config options ) { 
		options.set("renderTo", wrapperId);
		options.set("contentEl", getComponent().getMarkupId() );
		options.set("title", "Hello there!");
		options.set("width", 400);
		options.set("collapsible", true);
		return new ExtClass(getExtClassName(), options);
	}
	
}
