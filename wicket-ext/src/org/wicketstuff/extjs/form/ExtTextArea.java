package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtTextAreaBehavior;

/**
 * TextArea component integrating Ext wicket <code>Ext.form.TextArea</code>
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtTextArea extends TextArea {

	public ExtTextArea(String id) {
		super(id);
		init();
	}
	
	public ExtTextArea( String id, IModel model ) { 
		super(id,model);
		init();
	}
	
	private void init() { 
		add( new ExtTextAreaBehavior() );
	}

}
