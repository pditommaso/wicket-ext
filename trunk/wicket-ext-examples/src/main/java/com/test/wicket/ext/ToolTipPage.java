package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.behavior.ExtQuickTipsBehavior;
import org.wicketstuff.extjs.behavior.ExtToolTipBehavior;

public class ToolTipPage extends WebPage {

	public ToolTipPage() { 
		
		ExtQuickTipsBehavior quickTip = new ExtQuickTipsBehavior("Lorem ipsum dolores, lorem ipsum dolores, lorem ipsum dolores, lorem...","Title");
		WebMarkupContainer container = new WebMarkupContainer("content");
		container.add(quickTip);
		
		ExtToolTipBehavior toolTip = new ExtToolTipBehavior("Lorem ipsum dolores, lorem ipsum dolores, lorem ipsum dolores");
		toolTip.setTitle("Title");
		toolTip.setTrackMouse(true);
		WebMarkupContainer container2 = new WebMarkupContainer("content2");
		container2.add(toolTip);
		
		ExtToolTipBehavior toolTip2 = new ExtToolTipBehavior("Lorem ipsum");
		toolTip2.setTitle("Draggable Title");
		toolTip2.setAutoHide(false);
		toolTip2.setDraggable(true);
		WebMarkupContainer container3 = new WebMarkupContainer("content3");
		container3.add(toolTip2);
		
		add( container  );
		add( container2 );
		add( container3 );
	}
	
}
