package org.wicketstuff.extjs.behavior;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.wicketstuff.extjs.Ext;

public abstract class ExtAbstractBehavior extends AbstractAjaxBehavior {

	@Override
	public void onBind() { 
		getComponent().setOutputMarkupId(true);
		Ext.addContribution(getComponent());
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		/* initialize the Ext blank image url */
		String url = RequestCycle.get().urlFor( Ext.Images.BLANK ).toString();
		response.renderOnDomReadyJavascript(String.format("Ext.BLANK_IMAGE_URL='%s'", url));
		
		CharSequence script = onDomReady();
		if( script != null ) { 
			response.renderOnDomReadyJavascript(script.toString());
		}
	}
	

	
	protected abstract CharSequence onDomReady();

}
 