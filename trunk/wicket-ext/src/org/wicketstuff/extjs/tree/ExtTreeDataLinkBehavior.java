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
	
	private ExtTreePanel provider;
	private static final String QUERY_PARAM = "node";

	public ExtTreeDataLinkBehavior(ExtTreePanel dataSource) {
		this.provider = dataSource;
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
				//TODO implements other iterator parameters
				Iterator<TreeNode> choices = provider.iterator(input);
				
				webResponse.write("[");
				while (choices.hasNext())
				{
					final TreeNode item = choices.next();
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
