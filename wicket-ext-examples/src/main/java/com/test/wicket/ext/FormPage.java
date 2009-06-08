package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.wicketstuff.extjs.form.ExtDateField;
import org.wicketstuff.extjs.form.ExtDropDownChoice;
import org.wicketstuff.extjs.form.ExtNumberField;
import org.wicketstuff.extjs.form.ExtTextArea;
import org.wicketstuff.extjs.form.ExtTextField;

public class FormPage extends WebPage {

	public FormPage() { 
		
		Form form = new Form("f");
		add(form);
		
		form.add( new ExtTextField("TextField") );
		form.add( new ExtDateField("DateField") );
		form.add( new ExtNumberField("NumberField") );
		form.add( new ExtDropDownChoice("DropDown", "One", "Two", "Three"));
		form.add( new ExtTextArea("TextArea") );
		
	}
	
}
