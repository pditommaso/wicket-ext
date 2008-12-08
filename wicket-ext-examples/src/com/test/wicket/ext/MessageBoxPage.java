package com.test.wicket.ext;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.wicketstuff.extjs.dialog.ExtAlertBehavior;
import org.wicketstuff.extjs.dialog.ExtConfirmBehavior;
import org.wicketstuff.extjs.dialog.ExtErrorBehavior;
import org.wicketstuff.extjs.dialog.ExtInfoBehavior;
import org.wicketstuff.extjs.dialog.ExtMessageDialog;
import org.wicketstuff.extjs.dialog.ExtPromptBehavior;

public class MessageBoxPage extends WebPage {

	public MessageBoxPage() { 
		
		final ExtMessageDialog dialog = new ExtMessageDialog("dialog") { 
			@Override
			protected void onClick( String button, String text ) { 
				System.out.println(String.format("Clicked: %s - Text: %s", button,text));
			}
		} ;
		add( dialog );
		
		/* Confirm link */
		add( new AjaxLink ("confirm") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dialog.confim(target,"Confirm","Are you really secure?");
			} }); 

		/* Alert link */
		add( new AjaxLink ("alert") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dialog.alert(target,"Warning","Warn this message");
			} }); 
		
		/* Prompt link */
		add( new AjaxLink ("prompt") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dialog.prompt(target,"Prompt","Enter a value");
			} }); 
		
		/* Prompt Multi-line link */
		add( new AjaxLink ("promptMultiline") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				dialog.prompt(target,"Prompt","Enter a value","Hello there",true);
			} }); 

		/*
		 * Confirm Dialog as behavior 
		 */
		Button btn = new Button("confirmBtn");
		btn.add( new ExtConfirmBehavior("Do you want really do that?") {
			@Override
			public void onClick(AjaxRequestTarget target, String pressedButton, String text) {
				System.out.println( "Confirm button: "  + pressedButton);
			}} );
		
		add(btn);

		/*
		 * Prompt Dialog as behavior 
		 */
		btn = new Button("promptBtn");
		btn.add( new ExtPromptBehavior("Enter a value here:") {
			@Override
			public void onClick(AjaxRequestTarget target, String pressedButton, String text) {
				System.out.println( String.format("Prompt button: %s - Text: %s", pressedButton, text ));
			}} );
		add(btn);

		/*
		 * Error Dialog as behavior 
		 */
		btn = new Button("errorBtn");
		btn.add( new ExtErrorBehavior("Shit happens!") {
			@Override
			public void onClick(AjaxRequestTarget target, String pressedButton, String text) {
				System.out.println( "Error button: "  + pressedButton);
			}} );
		add(btn);

		/*
		 * Info Dialog as behavior 
		 */
		btn = new Button("infoBtn");
		btn.add( new ExtInfoBehavior("Hello world!") {
			@Override
			public void onClick(AjaxRequestTarget target, String pressedButton, String text) {
				System.out.println( "Info button: "  + pressedButton);
			}} );
		add(btn);
		
		/*
		 * Alert Dialog as behavior 
		 */
		btn = new Button("alertBtn");
		btn.add( new ExtAlertBehavior("Something is happened") {
			@Override
			public void onClick(AjaxRequestTarget target, String pressedButton, String text) {
				System.out.println( "Warning button: "  + pressedButton);
			}} );
		add(btn);
		
	}
	
}
