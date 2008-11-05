package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.behavior.ExtPanelBehavior;

public class PanelPage extends WebPage {

	public PanelPage() { 
		
		
		add( new WebMarkupContainer("content").add( new ExtPanelBehavior() )  );

	}
	
}
