package org.wicketstuff.extjs.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;

/**
 * Wrapper component for <code>Ext.Window</code> class
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtWindow extends WebMarkupContainer {

	private final Config config;

	private String wrapId;
	
	{ 
		config = new Config();
		config.set("width", 400);
	}
	
	public ExtWindow(String id) {
		super(id);
		init();
	}
	
	public ExtWindow(String id, IModel model) {
		super(id, model);
		init();
	}	
	
	private void init() { 
		Ext.addContribution(this);
		setOutputMarkupId(true);
		add( new AttributeAppender("class", new Model("x-hidden"), " " ));
	}
	

	@Override
	protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		wrapId = "wrap:" + getMarkupId();
		getResponse().write(String.format("<div id='%s'>", wrapId));
		super.onComponentTagBody(markupStream, openTag);
		getResponse().write("</div>");
	}


	public void show( AjaxRequestTarget target ) { 
		target.addComponent(this);	// <-- force component reloading to refresh content 
		config.set("contentEl", wrapId );
		ExtClass win = new ExtClass("Ext.Window", config );
		target.appendJavascript(String.format("%s.show();", win));
	}


	
	public void setTitle( String title ) { 
		config.set("title", title);
	}
	
	public void setModal( boolean modal ) { 
		config.set("modal", modal);
	}
	
	public void setWidth( int width ) { 
		config.set("width", width );
	}
	
	public void setHeight( int height ) { 
		config.set("height", height);
	}
}
