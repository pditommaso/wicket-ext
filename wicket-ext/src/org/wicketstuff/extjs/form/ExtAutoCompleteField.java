package org.wicketstuff.extjs.form;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.XTemplate;
import org.wicketstuff.extjs.behavior.ExtAutoCompleteBehavior;
import org.wicketstuff.extjs.behavior.ExtDataStoreBehavior;
import org.wicketstuff.extjs.util.ObjectMapper;

public abstract class ExtAutoCompleteField<T> extends TextField {


	private ExtDataStoreBehavior<T> data;
	
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

		
		ObjectMapper<T> mapper = new ObjectMapper<T>() {
			public Map<String,Object> mapObject( Object object, int index ) { 
				Map<String,Object> result = new HashMap<String, Object>();
				result.put( "id", choiceRenderer.getIdValue(object, index) );
				result.put( "display", choiceRenderer.getDisplayValue(object) );
				return result;
			}}; 
		/*
		 * Data store to retrieve data
		 */
		data = new ExtDataStoreBehavior<T>(mapper) {

			@Override
			protected Iterator<T> getChoices(String input) {
				return ExtAutoCompleteField.this.getChoices(input);
			}};
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
