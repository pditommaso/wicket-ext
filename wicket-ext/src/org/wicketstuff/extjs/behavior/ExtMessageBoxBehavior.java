package org.wicketstuff.extjs.behavior;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ExtMessageBox;

public class ExtMessageBoxBehavior extends ExtAbstractBehavior {

	String title = "Confirm";
	
	String message = "Are you sure you want to do that?";
	
	@Override
	public void onBind() { 
		getComponent().setOutputMarkupId(true);
	}
	
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		/*
		 * attach the behavior to client side DOM object
		 */
		ExtMessageBox dialog = ExtMessageBox.confirm( title,  message, this );
		
		ExtFunction function = new ExtFunction( dialog.toString() );
		
		String id = getComponent().getMarkupId();
		String ext = String.format("Ext.get('%s').on('click', %s )", id, function );
		response.renderOnDomReadyJavascript( ext.toString() );
	}
	
	
	final public void onRequest() {
		String pressedButton = getComponent().getRequest().getParameter("btn");
		onClick( pressedButton );
	}

	
	/**
	 * Public callback method. Override this method to intercet the Mesasge Dialog button click event 
	 * 
	 * @param pressedButton
	 */
	public void onClick(String pressedButton) { 
		
	}


	@Override
	protected CharSequence onDomReady() {
		// TODO Auto-generated method stub
		return null;
	};

}
