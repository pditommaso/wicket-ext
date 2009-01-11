/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketstuff.extjs.behavior;

import org.apache.wicket.Application;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WicketEventReference;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IDebugSettings;
import org.apache.wicket.util.string.JavascriptUtils;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;

/** 
 * Base class for Ext behaviors 
 * 
 * @author Paolo Di Tommaso
 *
 */
public abstract class ExtAbstractBehavior extends AbstractAjaxBehavior {

	
	private final Config config;
	
	public ExtAbstractBehavior() {
		config = new Config();
	}

	public ExtAbstractBehavior( Config options ) {
		config = options;
	}
	
	/**
	 * Bind the compont to this behavior. This method will add Ext resources contribution
	 */
	@Override
	public void onBind() { 
		getComponent().setOutputMarkupId(true);
	}
	
	/**
	 * Render the Ext component in the page header on a DOM Ready event
	 * 
	 * @see {@link #renderExtScript()}
	 */
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		final IDebugSettings debugSettings = Application.get().getDebugSettings();
		/*
		 * required wicket ajax support
		 */
		response.renderJavascriptReference(WicketEventReference.INSTANCE);
		response.renderJavascriptReference(WicketAjaxReference.INSTANCE);

		if (debugSettings.isAjaxDebugModeEnabled())
		{
			response.renderJavascriptReference(new JavascriptResourceReference(AbstractDefaultAjaxBehavior.class, "wicket-ajax-debug.js"));
			response.renderJavascript("wicketAjaxDebugEnable=true;", "wicket-ajax-debug-enable");
	}
	
		/*
		 * Ext resources contributions
		 */
		IHeaderContributor[] contributions = Ext.bundle().getContributions();
		for( IHeaderContributor contrib : contributions ) {
			contrib.renderHead(response);
		}

	}

	@Override
	protected void onComponentRendered() {

		CharSequence script = renderExtScript();
		if( script != null ) { 
			Response response = getComponent().getResponse();
			response.write( "\n" );
			response.write( JavascriptUtils.SCRIPT_OPEN_TAG );
			response.write( String.format("Ext.onReady(function(){%s} )", script) );
			response.write( JavascriptUtils.SCRIPT_CLOSE_TAG );
		}
	}
	

	/**
	 * @return the configuration to be provided to the Ext component
	 */
	public final Config config() {
		return config;
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
 