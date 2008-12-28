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
		config.putIfNotExists("rootVisible", false);
		config.putIfNotExists("loader", new ExtClass("Ext.tree.TreeLoader"));

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
