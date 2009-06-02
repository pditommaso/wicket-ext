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

package org.wicketstuff.extjs.tree;

import java.util.Iterator;

import org.apache.wicket.Application;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.protocol.http.WebResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Behavior providing data to ExtTreePanel
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtTreeDataLinkBehavior extends AbstractAjaxBehavior {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(ExtTreeDataLinkBehavior.class);

	private ExtAsyncTreePanel provider;
	private static final String QUERY_PARAM = "node";

	public ExtTreeDataLinkBehavior(ExtAsyncTreePanel dataSource) {
		provider = dataSource;
	}

	public void onRequest() {
		Request request = getComponent().getRequestCycle().getRequest();
        final String input = request.getParameter(QUERY_PARAM);
        if( log.isDebugEnabled() ) {
        	log.debug("Store request input: '{}'", input);
        }

		IRequestTarget target = new IRequestTarget() {

			public void respond(RequestCycle requestCycle) {
				WebResponse webResponse = (WebResponse)requestCycle.getResponse();

				// Determine encoding
				final String encoding = Application.get().getRequestCycleSettings().getResponseRequestEncoding();
				webResponse.setCharacterEncoding(encoding);
				webResponse.setContentType("text/plain; charset=" + encoding);

				// Make sure it is not cached by a
				webResponse.setHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
				webResponse.setHeader("Cache-Control", "no-cache, must-revalidate");
				webResponse.setHeader("Pragma", "no-cache");
				ExtTreeNode expandedNode = provider.findNode(input);
				Iterator<ExtTreeNode> choices = provider.iterator(expandedNode);
								
				webResponse.write("[");
				while (choices.hasNext()) {
					final ExtTreeNode item = choices.next();
					expandedNode.appendChild(item);
					webResponse.write(item.toString());
					if(choices.hasNext()) webResponse.write(",");
				}
				webResponse.write("]");
			}

			public void detach(RequestCycle requestCycle)
			{
			}
		};
		getComponent().getRequestCycle().setRequestTarget(target);
	}
}
