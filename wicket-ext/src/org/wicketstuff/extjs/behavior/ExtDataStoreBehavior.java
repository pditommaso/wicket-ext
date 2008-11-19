package org.wicketstuff.extjs.behavior;

import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.data.ExtDataSource;
import org.wicketstuff.extjs.data.ExtXmlReader;
import org.wicketstuff.extjs.data.Store;
import org.wicketstuff.extjs.util.ObjectMapper;
import org.wicketstuff.extjs.util.XmlRenderer;

/**
 * Behavior providing datasource capabilities to EXT components 
 * 
 * @author Paolo Di Tommaso
 *
 */
public abstract class ExtDataStoreBehavior<T> extends ExtAbstractBehavior implements ExtDataSource {

	private static final Logger log = LoggerFactory.getLogger(ExtDataStoreBehavior.class);
	
	private XmlRenderer<T> response;

	private ExtXmlReader extXmlReader;
	private Store store;
	
	private static final String QUERY_PARAM = "q";

	private ObjectMapper<T> mapper;
	
	
	/* TODO instead of IChoiceRenderer we should use a more adeguate render interface */
	public ExtDataStoreBehavior(ObjectMapper<T> mapper) {
		this.mapper = mapper;
		this.response = new XmlRenderer<T>(mapper);
	}
	
	final public Store getStore() {
		if( store == null ) { 
			store = createStore();
		}
		return store;
	}
	
	public void setStore( Store store ) { 
		this.store = store;
	}
	
	public String getQueryParam() { 
		return QUERY_PARAM;
	}
	

	private Store createStore() {
		/* 
		 * record definition for reader 
		 */
		Map<String,Object> sample = mapper.mapObject(null, 0);
		
		Object[] record = new Object[sample.size()];
		int i=0;
		Iterator<String> iterator = sample.keySet().iterator();
		while( iterator.hasNext() ) { 
			record[i++] = new Config("name", iterator.next() );
		}
		
		/*
		 * Xml reader to fetch the response data 
		 */
		
		Config config = new Config()	
			.set("record","item")
			.set("id","id");
		extXmlReader = new ExtXmlReader( config, record );
		
		
		/*
		 * main datastore object 
		 */
		Config storeConfig = new Config()
			.set( "url", getCallbackUrl() )
			.set( "reader", extXmlReader );
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

				Iterator<T> choices = getChoices(input);
				response.renderHeader(webResponse);
				while (choices.hasNext())
				{
					final T item = choices.next();
					response.render(item, webResponse, input);
				}
				response.renderFooter(webResponse);
			}

			public void detach(RequestCycle requestCycle)
			{
			}


		};
		getComponent().getRequestCycle().setRequestTarget(target);			
	}	

	
	abstract protected Iterator<T> getChoices(String input);


	
	@Override
	protected CharSequence onDomReady() { 
		return null;
	}
}
