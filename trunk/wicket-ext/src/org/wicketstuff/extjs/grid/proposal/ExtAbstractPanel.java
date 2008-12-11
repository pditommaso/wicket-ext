package org.wicketstuff.extjs.grid.proposal;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;

public class ExtAbstractPanel extends WebMarkupContainer {

	private static final long serialVersionUID = 1L;
	protected Config config ;
	
	{
		config = new Config();
	}
	
	public ExtAbstractPanel(String id, IModel model) {
		super(id, model);
	}

	public ExtAbstractPanel(String id) {
		super(id);
	}
	
	public ExtAbstractPanel setWidth( int width ) { 
		config.set("width", width );
		return this;
	}
	
	public ExtAbstractPanel setHeight( int height ) { 
		config.set("height", height );
		return this;
	}

	public ExtAbstractPanel setTitle( String title ) { 
		config.set("title", title );
		return this;
	}
	
	public ExtAbstractPanel setCollapsible(boolean collapsible ) { 
		config.set("collapsible", collapsible );
		return this;
	}
	
	public ExtAbstractPanel setAnimCollapse(boolean animCollapse ) { 
		config.set("animCollapse", animCollapse );
		return this;
	}
	
	public ExtAbstractPanel setFrame(boolean frame ) { 
		config.set("frame", frame );
		return this;
	}
}
