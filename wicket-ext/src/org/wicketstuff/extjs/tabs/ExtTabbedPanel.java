package org.wicketstuff.extjs.tabs;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.behavior.ExtTabPanelBehavior;

public class ExtTabbedPanel extends MarkupContainer {

  /**
   *
   */
  private static final long serialVersionUID = 1L;


  public ExtTabbedPanel(String id, IModel model) {
    super(id, model);
    init();
  }


  public ExtTabbedPanel(String id) {
    super(id);
    init();
  }


  private void init() {
    add(new ExtTabPanelBehavior());
  }
}
