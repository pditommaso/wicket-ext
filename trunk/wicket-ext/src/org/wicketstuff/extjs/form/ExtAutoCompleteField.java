package org.wicketstuff.extjs.form;

import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.XTemplate;
import org.wicketstuff.extjs.behavior.ExtAutoCompleteBehavior;
import org.wicketstuff.extjs.behavior.ExtDataLinkBehavior;
import org.wicketstuff.extjs.data.DataProvider;
import org.wicketstuff.extjs.data.ObjectMap;

public abstract class ExtAutoCompleteField<T> extends TextField implements DataProvider<T> {

	private ExtDataLinkBehavior<T> data;
	
	private IChoiceRenderer choiceRenderer = new ChoiceRenderer();
	
	private XTemplate template;
	
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
		 * Ext template to render the single item html 
		 */
		template = new XTemplate(
					"<tpl for=\".\"><div class=\"search-item\">",
		            "<h3>{display}</h3>",
		            "{excerpt}",
		        	"</div></tpl>");			
		
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
			}};
		autocomplete.setTemplate(template);
		autocomplete.setData(data);
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

	final public Iterator<T> iterator( String filter, String direction, Integer start, Integer count ) { 
		return getChoices(filter);
	}
	
	final public Long totalRecords() { return null; }
	
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


}
