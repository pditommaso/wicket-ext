package org.wicketstuff.extjs.behavior;

import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;

/**
 * Wrapper class for <code>Ext.ToolTip</code>
 * 
 * @author vmoreau
 * @author Paolo Di Tommaso
 *
 */
public class ExtToolTipBehavior extends ExtComponentBehavior {

	private static final long serialVersionUID = 1L;

	/**
	 * @param text the string value to be displayed in the tooltip
	 */
	public ExtToolTipBehavior(String text) {
		super("Ext.ToolTip");
		options().set("html", text);
	}

	/**
	 * Create the tooltip using a Wicket model to specify the tip text.
	 *  
	 * @param text the model which object will be displayed in the tooltip as text
	 */
	public ExtToolTipBehavior(IModel text) {
		super("Ext.ToolTip");
		options().set("html", text);
	}

	/**
	 * Create the tooltip using a Wicket model to specify the tip text.
	 * 
	 * @param options the Ext configuration object
	 * @param text the model which object will be displayed in the tooltip as text
	 */
	public ExtToolTipBehavior(Config options,IModel text) {
		super("Ext.ToolTip", options);
		options().set("html", text);
	}
	
	@Override
	protected String getApplyMethod() { 
		return "target";
	}

	public void setTitle(String title) {
		options().set("title", title);
	}

	public void setTitle(IModel title) {
		options().set("title", title);
	}
	
	
	public void setAutoHide(boolean autoHide) {
		options().set("autoHide", autoHide);
		options().set("closable", !autoHide);
	}	
	
	public void setDraggable(boolean draggable) {
		options().set("draggable", draggable);
	}
	
	public void setTrackMouse(boolean trackMouse) {
		options().set("trackMouse", trackMouse);
	}
	
	
}

