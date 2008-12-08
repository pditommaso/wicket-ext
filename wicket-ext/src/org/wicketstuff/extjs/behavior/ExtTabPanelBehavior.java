package org.wicketstuff.extjs.behavior;

import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;

public class ExtTabPanelBehavior extends ExtComponentBehavior {

	{
		defaultOptions.set("autoTabs", true);
		defaultOptions.set("autoWidth", true);
		defaultOptions.set("deferredRender", false);
	}

	public ExtTabPanelBehavior() {
		super("Ext.TabPanel");
	}

	@Override
	public void beforeRender(Component component) {
		super.beforeRender(component);
		component.setOutputMarkupId(true);
		for (Iterator iter = ((MarkupContainer) component).iterator(); iter
				.hasNext();) {
			Component child = (Component) iter.next();
			child.setOutputMarkupId(true);
			child.add(new AttributeAppender("class", new Model("x-tab"), " ") {

				@Override
				public boolean isTemporary() {
					return true;
				}

			});
		}
	}

	@Override
	protected void onExtConfig( Config options ) { 
		options.set("activeTab", 0);	// <-- this value could be defined programmatically
	}

}
