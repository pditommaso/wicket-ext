package org.wicketstuff.extjs.behavior;

import java.util.Iterator;

import org.apache.wicket.Application;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.protocol.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtArray;
import org.wicketstuff.extjs.data.IExtDataSource;
import org.wicketstuff.extjs.data.Store;
import org.wicketstuff.extjs.data.XmlReader;

/**
 * Behavior providing datasource capabilities to EXT components 
 * 
 * @author Paolo Di Tommaso
 *
 */
public abstract class ExtDataStoreBehavior extends ExtAbstractBehavior implements IExtDataSource {

	private static final Logger log = LoggerFactory.getLogger(ExtDataStoreBehavior.class);
	
	private XmlRenderer responseRenderer;
	private IChoiceRenderer choiceRenderer;

	private XmlReader xmlReader;
	private Store store;
	
	private static final String QUERY_PARAM = "q";
	
	
	/* TODO instead of IChoiceRenderer we should use a more adeguate render interface */
	public ExtDataStoreBehavior(IChoiceRenderer renderer) {
		responseRenderer = new XmlRenderer();
  		choiceRenderer = renderer;
	}
	
	public Store getStore() {
		
		if( store == null ) { 
			store = createStore();
		}
		
		return store;
	}
	
	public String getQueryParam() { 
		return QUERY_PARAM;
	}
	

	private Store createStore() {
		/* 
		 * record definition for reader 
		 */
		ExtArray record = new ExtArray (  
				new Config("name", "display") 
		) ;
		
		/*
		 * Xml reader to fetch the response data 
		 */
		
		Config config = new Config()	
			.set("record","item")
			.set("id","id");
		xmlReader = new XmlReader( config, record );
		
		
		/*
		 * main datastore object 
		 */
		Config storeConfig = new Config()
			.set( "url", getCallbackUrl() )
			.set( "reader", xmlReader );
		return new Store( storeConfig );
	}

	public void onRequest() {
        final String input = getComponent().getRequestCycle().getRequest().getParameter(QUERY_PARAM);
        if( log.isDebugEnabled() ) { 
        	log.debug("Store request input: '%s'", input);
        }

		IRequestTarget target = new IRequestTarget()
		{

			public void respond(RequestCycle requestCycle)
			{
				
				WebResponse webResponse = (WebResponse)requestCycle.getResponse();
				
				// Determine encoding
				final String encoding = Application.get().getRequestCycleSettings().getResponseRequestEncoding();
				webResponse.setCharacterEncoding(encoding);
				webResponse.setContentType("text/xml; charset=" + encoding);

				// Make sure it is not cached by a
				webResponse.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
				webResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
				webResponse.setHeader("Pragma", "no-cache");

				Iterator<?> choices = getChoices(input);
				responseRenderer.renderHeader(webResponse);
				while (choices.hasNext())
				{
					final Object item = choices.next();
					responseRenderer.render(item, webResponse, input);
				}
				responseRenderer.renderFooter(webResponse);
			}

			public void detach(RequestCycle requestCycle)
			{
			}


		};
		getComponent().getRequestCycle().setRequestTarget(target);			
	}	

	
	abstract protected Iterator<?> getChoices(String input);


	class XmlRenderer implements IAutoCompleteRenderer {

		private int index; 
		
		public void render(Object object, Response response, String criteria) {
			StringBuilder result = new StringBuilder()
					.append("<item>")
					.append("<id>") .append( choiceRenderer.getIdValue(object, index++)) .append("</id>")
					.append("<display>") .append( choiceRenderer.getDisplayValue(object)) .append("</display>")
					.append("</item>");
			response.write( result );
		}

		public void renderHeader(Response response) {
			index = 0;
			response.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			response.write("<dataset>");
		}
		
		public void renderFooter(Response response) {
			response.write("</dataset>");
		}
	}
	
	@Override
	protected CharSequence onDomReady() { 
		return null;
	}
}
