package org.wicketstuff.extjs.form;

import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.wicketstuff.extjs.XTemplate;
import org.wicketstuff.extjs.behavior.ExtAutoCompleteBehavior;
import org.wicketstuff.extjs.behavior.ExtDataStoreBehavior;

public abstract class ExtAutoCompleteField extends TextField {

	private ExtDataStoreBehavior data;

	
	private XTemplate template;
	
	public ExtAutoCompleteField(String id) {
		super(id);

		/*
		 * Ext template to render the single item html 
		 */
		template = new XTemplate(
					"<tpl for=\".\"><div class=\"search-item\">",
		            "<h3><span>{display}</h3>",
		            "{excerpt}",
		        	"</div></tpl>");			

		/*
		 * the render for choices
		 */
		IChoiceRenderer renderer = new IChoiceRenderer() {

			public Object getDisplayValue(Object object) {
				return object;
			}

			public String getIdValue(Object object, int index) {
				return String.valueOf(index);
			}} ;
			
		/*
		 * Data store to retrieve data
		 */
		data = new ExtDataStoreBehavior(renderer) {

			@Override
			protected Iterator<?> getChoices(String input) {
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
			} };
		autocomplete.setTemplate(template);
		autocomplete.setData(data);
		add(autocomplete);
	}
	
	
	protected abstract Iterator<?> getChoices(final String input);


	/**
	 * Callback to respond on user selection 
	 * 
	 * @param target the current {@link AjaxRequestTarget} instance
	 * @param key the user entered search value
	 */
	protected void onSelect( AjaxRequestTarget target, String key ) { 
		
	} 
	


}
