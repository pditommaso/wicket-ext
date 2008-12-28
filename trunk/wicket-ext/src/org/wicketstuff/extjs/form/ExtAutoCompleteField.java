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

import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.ExtTemplate;
import org.wicketstuff.extjs.ITemplate;
import org.wicketstuff.extjs.XTemplate;
import org.wicketstuff.extjs.behavior.ExtAutoCompleteBehavior;
import org.wicketstuff.extjs.behavior.ExtDataLinkBehavior;
import org.wicketstuff.extjs.data.IDataSource;
import org.wicketstuff.extjs.data.ExtDataLink;
import org.wicketstuff.extjs.data.ObjectMap;

public abstract class ExtAutoCompleteField<T> extends TextField implements IDataSource<T> {

	private final static ITemplate DEFAULT_TEMPLATE = new ITemplate() {

		public XTemplate getXTemplate() {
			return new XTemplate(
					"<tpl for=\".\"><div class=\"search-item\">",
		            "<h3>{display}</h3>",
		            "{excerpt}",
		        	"</div></tpl>");
		}

		public String getItemSelector() {
			return "div.search-item";
		}

	};

	private ExtDataLinkBehavior<T> data;

	private IChoiceRenderer choiceRenderer = new ChoiceRenderer();

	private ExtTemplate template;

	public ExtAutoCompleteField(String id) {
		super(id);
		init();
	}

	public ExtAutoCompleteField( String id, IModel model ) {
		super(id);
		init();
	}


	private void init() {

		/*
		 * Data store to retrieve data
		 */
		data = new ExtDataLinkBehavior<T>(this);
		add( data );

		/*
		 * teh autocomplete behavior
		 */
		ExtAutoCompleteBehavior autocomplete = new ExtAutoCompleteBehavior() {
			@Override
			protected void onSelect(AjaxRequestTarget target, String key) {
				ExtAutoCompleteField.this.onSelect(target, key);
			}

			@Override
			protected ITemplate getTemplate() { return template != null ? template : DEFAULT_TEMPLATE; }

			@Override
			protected ExtDataLink getDataLink() { return data; }

		};
		add(autocomplete);

	}


	public ObjectMap mapObject( T object, Integer index  ) {
		ObjectMap result = new ObjectMap("id","display");
		if( object != null ) {
			result.put( "id", choiceRenderer.getIdValue(object, index) );
			result.put( "display", choiceRenderer.getDisplayValue(object) );
		}
		return result;
	}

	final public Iterator<T> iterator( Query query ) {
		return getChoices( query != null ? query.getFilter() : null );
	}

	final public Integer totalRecords() { return null; }

	protected abstract Iterator<T> getChoices(final String input);


	/**
	 * Callback to respond on user selection
	 *
	 * @param target the current {@link AjaxRequestTarget} instance
	 * @param key the user entered search value
	 */
	protected void onSelect( AjaxRequestTarget target, String key ) {

	}

	public IChoiceRenderer getChoiceRenderer() {
		return choiceRenderer;
	}

	public void setChoiceRenderer(IChoiceRenderer choiceRenderer) {
		this.choiceRenderer = choiceRenderer;
	}

	/**
	 * Define the template to be used for this componenent
	 *
	 * @param template instance reference to the {@link ExtTemplate} component containg the template to be used
	 */
	public void setTemplate( ExtTemplate template ) {
		this.template = template;
	}
}
