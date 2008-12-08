package org.wicketstuff.extjs.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

public class ExtQuickTipsBehavior extends ExtAbstractBehavior {

	private static final long serialVersionUID = 1L;
	private String title = null;
	private String texte = null;
	private int width = 0;
	
	public ExtQuickTipsBehavior(String pTexte) {
		super();
		texte = pTexte;
	}
	
	public ExtQuickTipsBehavior(String pTexte, String pTitle) {
		super();
		texte = pTexte;
		title = pTitle;
	}
	
	@Override
	public void onBind() { 
		getComponent().add(new AttributeModifier("ext:qtip", true, new Model(texte)));
		if(title!=null) getComponent().add(new AttributeAppender("ext:qtitle", true, new Model(title), " "));
		if(width > 0) getComponent().add(new AttributeAppender("ext:qwidth", true, new Model(width), " "));
		super.onBind();
	}

	public String getTitle() {
		return title;
	}

	public ExtQuickTipsBehavior setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getTexte() {
		return texte;
	}

	public ExtQuickTipsBehavior setTexte(String texte) {
		this.texte = texte;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public ExtQuickTipsBehavior setWidth(int width) {
		this.width = width;
		return this;
	}

	@Override
	protected CharSequence renderExtScript() {
		return "Ext.QuickTips.init()";
	}	
}
