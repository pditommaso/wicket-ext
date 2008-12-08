package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.behavior.ExtQuickTipsBehavior;
import org.wicketstuff.extjs.behavior.ExtToolTipBehavior;

public class ToolTipPage extends WebPage {

	public ToolTipPage() { 
		
		ExtQuickTipsBehavior quickTip = new ExtQuickTipsBehavior("Et le texte blablabl ablablablab la sdfk ùjsdùg jsdqlù gjùsdq gjùsd gqjùqsd gj","Le titre");
		WebMarkupContainer container = new WebMarkupContainer("content");
		container.add(quickTip);
		
		ExtToolTipBehavior toolTip = new ExtToolTipBehavior("Et le texte blablabl ablabd gqjùqsd gj");
		toolTip.setTitle("Title");
		toolTip.setTrackMouse(true);
		WebMarkupContainer container2 = new WebMarkupContainer("content2");
		container2.add(toolTip);
		
		ExtToolTipBehavior toolTip2 = new ExtToolTipBehavior("mkh sdmqghhgsdqm");
		toolTip2.setTitle("Title draggable");
		toolTip2.setAutoHide(false);
		toolTip2.setDraggable(true);
		WebMarkupContainer container3 = new WebMarkupContainer("content3");
		container3.add(toolTip2);
		
		add( container  );
		add( container2 );
		add( container3 );
	}
	
}
