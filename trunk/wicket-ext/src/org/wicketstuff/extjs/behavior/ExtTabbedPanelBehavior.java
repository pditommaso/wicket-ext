package org.wicketstuff.extjs.behavior;

import java.util.Iterator;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTabbedPanelBehavior extends ExtComponentBehavior {
  {
    defaultOptions.set("autoTabs", true);
    defaultOptions.set("autoWidth", true);
    defaultOptions.set("deferredRender", false);
}

  private boolean autoTagComponents;

  public ExtTabbedPanelBehavior(boolean autoTagComponents) {
    super("Ext.TabPanel");
    this.autoTagComponents = autoTagComponents;
  }

  public void beforeRender(Component component) {
    super.beforeRender(component);
    component.setOutputMarkupId(true);
    
    if (autoTagComponents) {
      ((MarkupContainer)component).visitChildren(MarkupContainer.class, new IVisitor(){
    
        public Object component(Component child) {
          if (child instanceof AbstractRepeater) {
            return null;
          }
          child.setOutputMarkupId(true);
          child.add(new AttributeAppender("class", new Model("x-tab"), " ") {
            @Override
            public boolean isTemporary() {
              return true;
            }
          });
          
          return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
        }});
    }
  }

  @Override
  protected void onExtConfig(Config options) {
    options.set("activeTab", 0); // <-- this value could be defined programmatically
  }



}
