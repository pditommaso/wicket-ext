package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.wicketstuff.extjs.form.ExtHtmlEditor;

public class HtmlEditorPage extends WebPage {

	String body = "ciao";
	
	public HtmlEditorPage() { 
		
		
		Form form = new Form("f", new CompoundPropertyModel(this));
		add(form);
		
		form.add( new ExtHtmlEditor("body")  );
		
		form.add ( new SubmitLink("save") {
			@Override
			public void onSubmit() {
				System.out.println( body );
			}
			
		} ); 
		
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
