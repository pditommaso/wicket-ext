package com.test.wicket.ext;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.behavior.ExtMessageBoxBehavior;
import org.wicketstuff.extjs.dialog.ExtMessageDialog;

public class MessageBoxPage extends WebPage {

	public MessageBoxPage() { 
		
		final ExtMessageDialog dialog = new ExtMessageDialog("dialog");
		add( dialog );
		
		add( new AjaxLink ("confirm") {

			@Override
			public void onClick(AjaxRequestTarget target) {

				dialog.confim(target);
			} }); 
		
		
		WebMarkupContainer container = new WebMarkupContainer("button");
		container.add( new ExtMessageBoxBehavior() {

			@Override
			public void onClick(String pressedButton) {
				// TODO Auto-generated method stub
				System.out.println( "Hello there >>> "  + pressedButton);
			} 
			
		}  );
		
		add( container );
		
	}
	
}
