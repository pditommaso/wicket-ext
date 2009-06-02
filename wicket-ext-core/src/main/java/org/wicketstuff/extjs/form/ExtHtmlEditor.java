/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;

/**
 * Ext based HTML editor. This component have to be applyed to a <code>TEXTAREA</code> markup element
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtHtmlEditor extends AbstractTextComponent {

	private Config extConfig = new Config();

	public ExtHtmlEditor(String id) {
		super(id);
		init();
	}

	public ExtHtmlEditor(String id,Config options) {
		super(id);
		this.extConfig = options;
		init();
	}

	public ExtHtmlEditor(String id, IModel model) {
		super(id, model);
		init();
	}

	private void init() {
		setWidth(500);
		setHeight(400);
		add( new ExtHtmlEditorBehavior(extConfig) );
	}

	public ExtHtmlEditor setWidth( int width ) { 
		extConfig.set("width", width);
		return this;
	}
	
	public ExtHtmlEditor setHeight( int height ) { 
		extConfig.set("height", height);
		return this;
	}
	
	public ExtHtmlEditor setSize( int width, int height ) { 
		setWidth(width);
		setHeight(height);
		return this;
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
		super("Ext.form.HtmlEditor",options);
	}

	/*
	 * due a bug in Ext HtmlEditor size have to be defined invoking specific method setSize/setWidth/setHeight
	 * see http://www.seancallan.com/?p=44
	 */
	@Override
	protected CharSequence onExtScript( Config config ) { 
		config.set("value", getComponent().getModelObject());
		
		// get the width and remove from configuration options;
		Integer width = config.get("width");
		Integer height = config.get("height");

		/* add the right rezie method to the main script */
		StringBuilder script = new StringBuilder( super.onExtScript(config) );
		if( width != null && height != null ) { 
			script.append(".setSize(") .append(width) .append(",") .append( height ) .append(")");
		}
		else if( width != null ) { 
			script.append(".setWidth(") .append(width) .append(")");
	}
		else if ( height != null ) { 
			script.append(".setHeight(") .append(height) .append(")");
	}
		return script;
	}


}