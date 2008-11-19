package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;

public class ExtHtmlEditor extends AbstractTextComponent {

	public ExtHtmlEditor(String id) {
		super(id);
		init();
	}

	private void init() {
		add( new ExtHtmlEditorBehavior() );
	}

	
	@Override
	protected final void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag)
	{
		replaceComponentTagBody(markupStream, openTag, "");
	}
	
}


class ExtHtmlEditorBehavior extends ExtComponentBehavior {


	public ExtHtmlEditorBehavior() {
		super("Ext.form.HtmlEditor");
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
		config.set("renderTo", getComponent().getMarkupId() );
		config.set("name", getComponent().getId() );
		return new ExtClass(getExtClassName(), config);
	}
	
	
}