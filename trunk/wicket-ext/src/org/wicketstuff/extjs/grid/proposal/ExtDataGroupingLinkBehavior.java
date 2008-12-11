package org.wicketstuff.extjs.grid.proposal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.protocol.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.data.DataProvider;
import org.wicketstuff.extjs.data.XmlReader;
import org.wicketstuff.extjs.util.XmlRenderer;

/**
 * Behavior providing datasource capabilities to Ext components 
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ExtDataGroupingLinkBehavior<T> extends AbstractAjaxBehavior implements ExtDataGroupingLink {

	private static final Logger log = LoggerFactory.getLogger(ExtDataGroupingLinkBehavior.class);
	
	private ExtGroupingStore store;
	private DataProvider<T> provider;
	private static final String QUERY_PARAM = "q";

	public ExtDataGroupingLinkBehavior(DataProvider<T> dataSource) {
		this.provider = dataSource;
	}
	
	
	/**
	 * @return the Ext <code>Store</code> component to manage the data connection
	 */
	final public ExtGroupingStore getStore() {
		if( store == null ) { 
			store = createStore();
		}
		return store;
	}
	
	
	/**
	 * @return defines the url parameter used to query data
	 */
	public String getQueryParam() { 
		return QUERY_PARAM;
	}
	

	private ExtGroupingStore createStore() {
		/* 
		 * record definition for reader 
		 */
		Map<String,Object> sample = provider.mapObject(null, null);
		
		Object[] record = {};
		if( sample != null ) { 
			record = new Object[ sample.size() ];
			int i=0;
			Iterator<String> iterator = sample.keySet().iterator();
			while( iterator.hasNext() ) { 
				record[i++] = new Config("name", iterator.next() );
			}
		}
		
		/*
		 * Xml reader to fetch the response data 
		 */
		
		Config config = new Config();	
		config.set("record", XmlRenderer.ITEM );
		config.set("id","id");
		//config.set("totalRecords", XmlRenderer.TOTAL_SIZE );
		XmlReader xmlReader = new XmlReader( config, record );
		
		
		/*
		 * main datastore object 
		 */
		Config storeConfig = new Config();
		storeConfig.set( "url", getCallbackUrl() );
		storeConfig.set( "reader", xmlReader );
		storeConfig.set( "groupField", "alpha" );
		
		HashMap<String,String> sorting = new HashMap<String, String>();
		sorting.put("field","alpha");
		sorting.put("direction","ASC");
		storeConfig.set( "sortInfo", sorting );
		//sortInfo:{field: 'company', direction: "ASC"},

		return new ExtGroupingStore( storeConfig );
	}

	private Integer num( String value, String message) { 
		if( value == null ) { 
			return null;
		}

		Integer result = null;
		try { 
			result = Integer.parseInt(value);
		} catch( NumberFormatException e ) { 
			log.warn(message,value);
		}
		return result;
		
	}
	
	public void onRequest() {
		Request request = getComponent().getRequestCycle().getRequest();
        final String input = request.getParameter(QUERY_PARAM);
        final Integer limit = num(request.getParameter("limit"), "Parameter 'limit' is not a valid number: {}");
        final Integer start = num(request.getParameter("start"), "Paramer 'start' is not a valid number: {}");
        if( log.isDebugEnabled() ) { 
        	log.debug("Store request input: '{}'", input);
        }

		IRequestTarget target = new IRequestTarget()
		{

			public void respond(RequestCycle requestCycle)
			{
				WebResponse webResponse = (WebResponse)requestCycle.getResponse();
				XmlRenderer<T> xml = new XmlRenderer<T>(provider);
				
				// Determine encoding
				final String encoding = Application.get().getRequestCycleSettings().getResponseRequestEncoding();
				webResponse.setCharacterEncoding(encoding);
				webResponse.setContentType("text/xml; charset=" + encoding);

				// Make sure it is not cached by a
				webResponse.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
				webResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
				webResponse.setHeader("Pragma", "no-cache");
				//TODO implements other iterator parameters
				Iterator<T> choices = provider.iterator(input,null,start,limit);
				xml.renderHeader(webResponse);
				while (choices.hasNext())
				{
					final T item = choices.next();
					xml.render(item, webResponse, input);
				}
				xml.renderFooter(webResponse);
			}

			public void detach(RequestCycle requestCycle)
			{
			}


		};
		getComponent().getRequestCycle().setRequestTarget(target);			
	}	


}
