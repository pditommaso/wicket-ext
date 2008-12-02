package org.wicketstuff.extjs.behavior;

import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTabPanelBehavior extends ExtComponentBehavior {

  public ExtTabPanelBehavior() {
    super("Ext.TabPanel");
  }

  public void beforeRender(Component component) {
    super.beforeRender(component);
    component.setOutputMarkupId(true);
    for(Iterator iter = ((MarkupContainer)component).iterator(); iter.hasNext();) {
      Component child = (Component)iter.next();
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
  protected ExtClass create(Config options) {
    options.set("applyTo", getComponent().getMarkupId());
    options.set("autoTabs", true);
    options.set("autoWidth", true);
    options.set("activeTab", 0);
    options.set("deferredRender", false);
    return new ExtClass(getExtClassName(), options);
  }



}
