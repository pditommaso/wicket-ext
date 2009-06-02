package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.ux.behavior.ExtTableGridBehavior;

public class TablePage extends WebPage {

	public TablePage() { 
		
		WebMarkupContainer table = new WebMarkupContainer("the-table");
		table.add(new ExtTableGridBehavior());
		add(table);

	}
	
}
