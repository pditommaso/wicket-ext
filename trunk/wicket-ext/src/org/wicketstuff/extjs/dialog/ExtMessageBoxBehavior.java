package org.wicketstuff.extjs.dialog;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ExtMethod;
import org.wicketstuff.extjs.behavior.ExtAbstractBehavior;
import org.wicketstuff.extjs.util.WicketCallBuilder;

/**
 * Behavior that renders an Ext dialog box on a component event
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtMessageBoxBehavior extends ExtAbstractBehavior {

	public ExtMessageBoxBehavior() { 
	}

	public ExtMessageBoxBehavior( MessageType type ) { 
		super(type.config());
	}

	public ExtMessageBoxBehavior( Config config ) { 
		super(config);
	}
	
	@Override
	final public void onEvent(AjaxRequestTarget target ) {
		String pressedButton = getComponent().getRequest().getParameter("btn");
		String text = getComponent().getRequest().getParameter("txt");
		onClick( target, pressedButton, text );
	}

	/**
	 * Public callback method. Override this method to intercet the Mesasge Dialog button click event 
	 * 
	 * @param pressedButton
	 */
	public void onClick(final AjaxRequestTarget target, final String pressedButton, final String text) { 
		
	}

	private ExtFunction callbackFn() {
		WicketCallBuilder call = new WicketCallBuilder(this.getCallbackUrl());
		call.append("btn", "btn");
		call.append("txt", "txt");
		return new ExtFunction( "btn,txt", call.toString() );
	}
	

	String dialogScript(Config options) { 
		options.set("fn", callbackFn());
		ExtMethod dialog = new ExtMethod("Ext.MessageBox.show", options );
		return dialog.toString();
	}
	
	@Override
	final protected CharSequence renderExtScript() {
		/*
		 * attach the behavior to client side DOM object
		 */
		ExtFunction function = new ExtFunction( dialogScript(options()) );

		String id = getComponent().getMarkupId();
		return String.format("Ext.get('%s').on('click', %s )", id, function.toString() );
	};

}
