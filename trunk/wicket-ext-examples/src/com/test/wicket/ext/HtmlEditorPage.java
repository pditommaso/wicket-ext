package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.wicketstuff.extjs.form.ExtHtmlEditor;

public class HtmlEditorPage extends WebPage {

	public HtmlEditorPage() { 
		
		Form form = new Form("f");
		add(form);
		
		form.add( new ExtHtmlEditor("HtmlEditor") );
		
	}
	
}
