package com.test.wicket.ext;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.tabs.ExtTabbedPanel;

/**
 * @author ittayd
 */
public class TabbedPanelPage extends WebPage {
  public TabbedPanelPage() {
    ExtTabbedPanel panel = new ExtTabbedPanel("tabs");
    add(panel);
    panel.add(new WebMarkupContainer("first"));
    panel.add(new WebMarkupContainer("second"));

  }
}

