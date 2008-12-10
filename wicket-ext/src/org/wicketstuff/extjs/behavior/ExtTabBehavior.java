package org.wicketstuff.extjs.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.IValueMap;

public class ExtTabBehavior extends AbstractBehavior {

  private String title;

  public ExtTabBehavior() {
  }

  public ExtTabBehavior(String title) {
    this.title = title;
  }
  
  @Override
  public void bind(Component component) {
    super.bind(component);
    component.add(new AttributeAppender("class", new Model("x-tab"), " "));
    if (title != null) {
      component.add(new AttributeModifier("title",true, new Model(title)));
    }
  }

}
