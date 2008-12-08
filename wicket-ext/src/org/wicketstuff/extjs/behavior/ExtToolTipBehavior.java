package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;

public class ExtToolTipBehavior extends ExtComponentBehavior {

	private static final long serialVersionUID = 1L;
	
	public ExtToolTipBehavior(String pTexte) {
		super("Ext.ToolTip");
		options().set("html", pTexte);
	}

	public ExtToolTipBehavior(Config options,String pTexte) {
		super("Ext.ToolTip", options);
		options().set("html", pTexte);
	}
	
	protected String getApplyMethod() { 
		return "target";
	}

	public void setTitle(String title) {
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

