package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.form.ExtAutoCompleteField;

public class AutoCompletePage extends WebPage {


	List<String> items = Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine");
	List<String> filteredList;

	public AutoCompletePage() {

		add( new ExtAutoCompleteField<String>("autocomplete") {

			@Override
			protected Iterator<String> getChoices(String input) {
				filteredList = new ArrayList<String>();
				for( String i : items ) {
					if( i.toLowerCase().startsWith(input.toLowerCase())) {
						filteredList.add(i);
					}
				}
				return filteredList.iterator();
			}

			@Override
			protected void onSelect(AjaxRequestTarget target, String key ) {
				System.out.println( "Selected item: " + filteredList.get(Integer.valueOf(key)) );
			}

		} );

	}

}
