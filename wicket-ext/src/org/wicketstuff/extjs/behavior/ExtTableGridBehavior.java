package org.wicketstuff.extjs.behavior;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTableGridBehavior extends ExtAbstractBehavior {

	public ExtTableGridBehavior() { 
	}


	@Override
	public void onBind() {
		super.onBind();
		getComponent().add( HeaderContributor.forJavaScript(new ResourceReference(ExtTableGridBehavior.class,"tablegrid.js")));
	}
	
	
	@Override
	public CharSequence onDomReady() {
		Config options = new Config("stripeRows",true);
		String id = getComponent().getMarkupId();
		ExtClass grid = new ExtClass("Ext.grid.TableGrid", id, options);
		
		return String.format("var grid=%s; grid.render();", grid);
	}


	public void onRequest() {
		// TODO Auto-generated method stub
		
	}
}
