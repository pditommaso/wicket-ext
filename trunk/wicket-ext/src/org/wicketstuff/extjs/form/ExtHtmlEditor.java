package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;

public class ExtHtmlEditor extends AbstractTextComponent {

	private Config extOptions = new Config();
	
	public ExtHtmlEditor(String id) {
		super(id);
		init();
	}
	
	public ExtHtmlEditor(String id,Config options) {
		super(id);
		this.extOptions = options;
		init();
	}

	public ExtHtmlEditor(String id, IModel model) {
		super(id, model);
		init();
	}

	private void init() {
		add( new ExtHtmlEditorBehavior(extOptions) );
	}

	@Override
	protected final void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag)
	{
		replaceComponentTagBody(markupStream, openTag, "");
	}
	
}


class ExtHtmlEditorBehavior extends ExtComponentBehavior {


	@Override
	public void onBind() { 
		super.onBind();
		
		if( !(getComponent() instanceof AbstractTextComponent) ) { 
			throw new RuntimeException("ExtHtmlEditorBehavior can be applyed only to AbstractTextComponent or subclass");
		}
	}
	
	public ExtHtmlEditorBehavior() {
		super("Ext.form.HtmlEditor");
	}
	
	public ExtHtmlEditorBehavior(Config options) {
		super("Ext.form.HtmlEditor");
		super.options().putAll(options);
	}

	@Override
	protected final void onComponentTag(final ComponentTag tag)
	{
		super.onComponentTag(tag);
		tag.setName("div");
		tag.getAttributes().remove("name");		
	}
	
	
	
	@Override
	public ExtClass create( Config config ) { 
		AbstractTextComponent comp = (AbstractTextComponent) getComponent();
		
		config.set("renderTo", getComponent().getMarkupId() );
		config.set("name", comp.getId() );
		config.set("value", comp.getValue());
		return new ExtClass(getExtClassName(), config);
	}
	
	
}