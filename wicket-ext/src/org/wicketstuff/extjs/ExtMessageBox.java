package org.wicketstuff.extjs;

import org.wicketstuff.extjs.behavior.ExtAbstractBehavior;
import org.wicketstuff.extjs.util.WicketCallBuilder;

/**
 * Wrapper class for <code>Ext.MessageBox</code> 
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtMessageBox extends ExtMethod {

	protected ExtMessageBox(String method, Object... params) {
		super(method, params);
	}


	private static ExtFunction callback( ExtAbstractBehavior behavior ) {
		WicketCallBuilder call = new WicketCallBuilder(behavior.getCallbackUrl());
		call.append("btn", "btn");

		return new ExtFunction( "btn", call.toString() );
	}
	
	
	public static ExtMessageBox confirm(String title, String message, ExtAbstractBehavior behavior ) {
		ExtMessageBox box = new ExtMessageBox( "Ext.MessageBox.confirm", title,  message, callback(behavior) );
		return box;
	}

	public static ExtMessageBox alert(String title, String message, ExtAbstractBehavior behavior ) { 
		ExtMessageBox box = new ExtMessageBox( "Ext.MessageBox.alert", title,  message, callback(behavior)  );
		return box;
	}

	public static ExtMessageBox prompt(String title, String message, ExtAbstractBehavior behavior ) { 
		ExtMessageBox box = new ExtMessageBox( "Ext.MessageBox.prompt", title,  message, callback(behavior)  );
		return box;
	}

	public static ExtMessageBox show( Config options, ExtAbstractBehavior behavior ) { 
		options.set("fn", callback(behavior) ); 
		ExtMessageBox box = new ExtMessageBox( "Ext.MessageBox.show", options );
		return box;
	}
	
}
