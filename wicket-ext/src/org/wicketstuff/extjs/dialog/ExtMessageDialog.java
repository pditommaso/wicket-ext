package org.wicketstuff.extjs.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ExtMethod;


/**
 * Integrate the <code>Ext.MessageBox</code> dialogs with Wicket
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtMessageDialog extends WebMarkupContainer {

	private AbstractAjaxBehavior handler;

	public ExtMessageDialog(String id) {
		super(id);
		init();
	}
	
	public ExtMessageDialog( String id, IModel model ) { 
		super(id,model);
		init();
	}

	private void init() {
		Ext.addContribution(this);
		
		handler = new AbstractAjaxBehavior() {

			public void onRequest() {
				String pressedButton = getComponent().getRequest().getParameter("btn");
				onClick( pressedButton );
			}}; 
		add(handler);
	}
	

	private String getCallbackUrl() { 
		return handler.getCallbackUrl().toString();
	}
	
	
	/**
	 * Callback handler on message box button click. Ovveride this method to intercept the message button events
	 * 
	 * @param pressedButton
	 */
	protected void onClick(final String pressedButton) {
		
	}

	public void confim( AjaxRequestTarget target ) { 
		ExtFunction callback = new ExtFunction("btn", "var u='"+getCallbackUrl()+"&btn='+btn; wicketAjaxGet(u,null,null);");
		
		ExtMethod dialog = new ExtMethod("Ext.MessageBox.confirm",
					"Confirm",
					"Are you sure you want to do that?",
					callback );

		target.appendJavascript(dialog.toString());
		
	}
	
	public void prompt( AjaxRequestTarget target ) { 
		//TODO  
	}
	
	public void alert( AjaxRequestTarget target ) { 
		//TODO
	}

}
