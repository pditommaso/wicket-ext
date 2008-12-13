package org.wicketstuff.extjs.behavior;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.util.WicketCallBuilder;

/**
 * another simple version of ExtTreePanel/ExtTreeNode/ExtTreePanelBehavior
 * 
 * support click event for the tree and the node. 
 * 
 * @author Jackie Zhong
 */
public class ExtTreePanelBehavior extends ExtComponentBehavior {

    private static final String QUERY_PARAM = "nodeid";

    {
		defaultOptions.set("rootVisible", false);
        defaultOptions.set("loader", new ExtClass("Ext.tree.TreeLoader"));
	}
	
	public ExtTreePanelBehavior() { 
		super("Ext.tree.TreePanel");
	}

	public ExtTreePanelBehavior(Config options) { 
		super("Ext.tree.TreePanel", options);
	}

    @Override
	public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.renderJavascriptReference(WicketAjaxReference.INSTANCE);
	}

	@Override
    protected void onExtConfig( Config config ) {
        String url = getCallbackUrl().toString();
        
        Map<String,Object> params = new HashMap<String, Object>();
        params.put(QUERY_PARAM, Ext.literal("node.id"));
        WicketCallBuilder ajax = new WicketCallBuilder(url);
        ajax.append(params);
        config.set("listeners", new Config("click", new ExtFunction("node", ajax.toString())));
    }

    @Override
    final protected void onEvent(AjaxRequestTarget target) {
        String nodeid = RequestCycle.get().getRequest().getParameter(QUERY_PARAM);
        onClick(nodeid, target);
    }

    protected void onClick(String nodeid, AjaxRequestTarget target) {
    }
}
