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

package org.wicketstuff.extjs.behavior;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Wrapper class for <code>Ext.QuickTips</code>
 *
 * @author vmoreau
 * @author Paolo Di Tommaso
 *
 */
public class ExtQuickTipsBehavior extends ExtAbstractBehavior {

	private static final long serialVersionUID = 1L;
	private IModel title = null;
	private IModel text = null;
	private int width = 0;


	/**
	 * @param text the string to be displayed in the tooltip
	 */
	public ExtQuickTipsBehavior(String pTexte) {
		this( new Model(pTexte) );
	}

	/**
	 * @param pTexte the string to be displayed in the tooltip
	 * @param pTitle
	 */
	public ExtQuickTipsBehavior(String text, String title) {
		this( new Model(text), new Model(title) );
	}


	public ExtQuickTipsBehavior(IModel text) {
		this.text = text;
	}

	public ExtQuickTipsBehavior(IModel text, IModel title) {
		this.text = text;
		this.title = title;
	}

	@Override
	public void onBind() {
		super.onBind();
		/* quick tip text */
		getComponent().add(new AttributeModifier("ext:qtip", true, text));

		/* quick tip title */
		if(title!=null) {
			getComponent().add(new AttributeAppender("ext:qtitle", true, title, " "));
		}

		/* quick tip width */
		IModel model = new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				return width > 0 ? width : null;
			} };
		getComponent().add(new AttributeModifier("ext:qwidth", true, model));

	}

	public String getTitle() {
		return title != null ? (String)title.getObject() : null;
	}

	public ExtQuickTipsBehavior setTitle(String title) {
		if( this.title == null ) {
			this.title = new Model();
		}
		this.title.setObject(title);
		return this;
	}

	public String getText() {
		return (String)text.getObject();
	}

	public ExtQuickTipsBehavior setTexte(String text) {
		this.text.setObject(text);
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
