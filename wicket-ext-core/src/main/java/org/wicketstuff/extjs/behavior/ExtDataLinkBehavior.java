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

import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.Application;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.util.string.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.data.IDataSource;
import org.wicketstuff.extjs.data.ExtDataLink;
import org.wicketstuff.extjs.data.GroupingStore;
import org.wicketstuff.extjs.data.Store;
import org.wicketstuff.extjs.data.XmlReader;
import org.wicketstuff.extjs.data.IDataSource.Query;
import org.wicketstuff.extjs.util.XmlRenderer;

/**
 * Behavior providing datasource capabilities to Ext components
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtDataLinkBehavior<T> extends AbstractAjaxBehavior implements ExtDataLink {

	private static final Logger log = LoggerFactory.getLogger(ExtDataLinkBehavior.class);

	private Store store;
	private IDataSource<T> provider;

	private String sortField;
	private String sortDirection;
	private String groupField;

	public ExtDataLinkBehavior(IDataSource<T> dataSource) {
		this.provider = dataSource;
	}


	/**
	 * @return the Ext <code>Store</code> component to manage the data connection
	 */
	final public Store getStore() {
		if( store == null ) {
			store = createStore();
		}
		return store;
	}


	/**
	 * @return defines the url parameter used to query data
	 */
	public String getParamQuery() {
		return "q".intern();
	}

	public String getParamStart() {
		return "start".intern();
	}

	public String getParamLimit() {
		return "limit".intern();
	}

	public String getParamDirection() {
		return "dir".intern();
	}

	public String getParamSort() {
		return "sort".intern();
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField( String groupField ) {
		this.groupField = groupField;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField( String sortField ) {
		this.sortField = sortField;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		if( sortDirection != null ) {
			sortDirection = sortDirection.toUpperCase();
			if( !"ASC".equals(sortDirection) && !"DESC".equals(sortDirection) ) {
				throw new IllegalArgumentException(String.format("Not a valid sort direction value: '%s'", sortDirection));
			}
		}
		this.sortDirection = sortDirection;
	}

	private Store createStore() {
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
		config.set("totalRecords", XmlRenderer.TOTAL_SIZE );
		XmlReader xmlReader = new XmlReader( config, record );

		/*
		 * main datastore object
		 */
		Config storeConfig = new Config();
		storeConfig.set( "url", getCallbackUrl() );
		storeConfig.set( "reader", xmlReader );
		boolean isGroupping = !Strings.isEmpty(groupField);
		if( isGroupping ) {
			storeConfig.set( "groupField", groupField );
		}

		if( !Strings.isEmpty(sortField) || !Strings.isEmpty(sortDirection)) {
			Config sorting = new Config();
			sorting.put("field", sortField );
			sorting.put("direction", sortDirection );
			storeConfig.set( "sortInfo", sorting );
			storeConfig.set("remoteSort", true);
		}

		return isGroupping ? new GroupingStore(storeConfig) : new Store( storeConfig );
	}

	public void onRequest() {
		Request request = getComponent().getRequestCycle().getRequest();

        final Query query = new Query()
        	.setFilter(request.getParameter(getParamQuery()))
        	.setLimit(request.getParameter(getParamLimit()))
        	.setStart(request.getParameter(getParamStart()))
        	.setSort(request.getParameter(getParamSort()))
        	.setDirection(request.getParameter(getParamDirection()));

        if( log.isDebugEnabled() ) {
        	log.debug("Store request input: '{}'", query);
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

				Iterator<T> choices = provider.iterator(query);
				xml.renderHeader(webResponse);
				while (choices.hasNext())
				{
					final T item = choices.next();
					xml.render(item, webResponse);
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
