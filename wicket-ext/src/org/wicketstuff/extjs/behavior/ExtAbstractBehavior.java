package org.wicketstuff.extjs.behavior;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;

/** 
 * Base class for Ext behaviors 
 * 
 * @author Paolo Di Tommaso
 *
 */
public abstract class ExtAbstractBehavior extends AbstractAjaxBehavior {

	
	final protected Config defaultOptions = new Config();
	
	private final Config options;
	
	public ExtAbstractBehavior() {
		this.options = new Config();
	}

	public ExtAbstractBehavior( Config options ) {
		this.options = options;
	}
	
	/**
	 * Bind the compont to this behavior. This method will add Ext resources contribution
	 */
	@Override
	public void onBind() { 
		getComponent().setOutputMarkupId(true);
		Ext.addContribution(getComponent());
	}
	
	/**
	 * Render the Ext component in the page header on a DOM Ready event
	 * 
	 * @see {@link #renderExtScript()}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		/* initialize the Ext blank image url */
		String url = RequestCycle.get().urlFor( Ext.Images.BLANK ).toString();
		response.renderOnDomReadyJavascript(String.format("Ext.BLANK_IMAGE_URL='%s'", url));
		
		CharSequence script = renderExtScript();
		if( script != null ) { 
			response.renderOnDomReadyJavascript(script.toString());
		}
	}
	

	/**
	 * @return the configuration to be provided to the Ext component
	 */
	public final Config options() { 
		return options;
	}

	/**
	 * @return the JavaScript Ext component to be rendered in the page header
	 */
	protected abstract CharSequence renderExtScript();
	
	
	public final void onRequest()
	{
		WebApplication app = (WebApplication)getComponent().getApplication();
		AjaxRequestTarget target = app.newAjaxRequestTarget(getComponent().getPage());
		RequestCycle.get().setRequestTarget(target);
		onEvent(target);
	}

	protected void onEvent(AjaxRequestTarget target) { }
	
	

}
 