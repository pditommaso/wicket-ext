package com.test.wicket.ext;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.form.ExtAutoCompleteField;

public class AutoCompletePage extends WebPage {

	public AutoCompletePage() { 
		
		add( new ExtAutoCompleteField<String>("autocomplete") {

			@Override
			protected Iterator<String> getChoices(String input) {
				return Arrays.asList("Pippo", "Pluto", "Paperino").iterator();
			} 
			
			@Override
			protected void onSelect(AjaxRequestTarget target, String key ) { 
				System.out.println( ">>>>>>> " + key );
			}

		} );

	}
	
}
