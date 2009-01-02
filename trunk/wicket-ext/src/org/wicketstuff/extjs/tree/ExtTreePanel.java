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

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.util.string.StringList;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.ExtContainer;
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
public class ExtTreePanel extends ExtContainer {

    private static final long serialVersionUID = 1L;

	private ExtTreeNode root;
    private boolean rootVisible = true;
    private boolean autoScroll = true;
    private boolean border = true;

    public ExtTreePanel(String id) {
        super(id);
        init();
    }

    public ExtTreePanel(String id, ExtTreeNode root) {
        this(id);
        this.root = root;
    }

    public ExtTreePanel setRoot(ExtTreeNode root) {
        this.root = root;
        return this;
    }

    public ExtTreeNode getRoot() {
        return root;
    }

    public boolean isAutoScroll() {
        return autoScroll;
    }

    public ExtTreePanel setAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
        return this;
    }

    public boolean isBorder() {
        return border;
    }

    public ExtTreePanel setBorder(boolean border) {
        this.border = border;
        return this;
    }

    public boolean isRootVisible() {
        return rootVisible;
    }

    public ExtTreePanel setRootVisible(boolean rootVisible) {
        this.rootVisible = rootVisible;
        return this;
    }

    private void init() {
    	
        setOutputMarkupId(true);
        add(new ExtTreePanelBehavior(config()));
    }

    private void doClick(String nodeid, AjaxRequestTarget target) {
        ExtTreeNode node = findNode(nodeid) ;
        onClick(node, target);
        node.onClick(target);
    }

    protected void onClick(ExtTreeNode node, AjaxRequestTarget target) {
    }
    
    protected CharSequence getExtScript(Config config) {
    	return "";
    }
    
    protected ExtTreeNode findNode(String nodeid){
    	StringList list = StringList.tokenize(nodeid, "_");
    	ExtTreeNode node = root;
        for (int i = 1; i < list.size(); i++)
            node = node.childNodes().get(Integer.valueOf(list.get(i)));
        
        return node;
    }
    
    private class ExtTreePanelBehavior extends ExtComponentBehavior {

        private static final long serialVersionUID = 1L;
    	private static final String QUERY_PARAM = "nodeid";

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
            
            if (root == null)
                throw new WicketRuntimeException("root node must be setted.");
            config.set("root", root);

            config.set("autoScroll", autoScroll);
            config.set("border", border);
            config.set("rootVisible", rootVisible);   
        }

        @Override
        final protected void onEvent(AjaxRequestTarget target) {
            String nodeid = RequestCycle.get().getRequest().getParameter(QUERY_PARAM);
            onClick(nodeid, target);
        }

        @Override
		protected CharSequence onExtScript(Config config) {
        	StringBuilder result = new StringBuilder(getExtScript(config));
        	
        	return result.append(super.onExtScript(config));
        }
		
        protected void onClick(String nodeid, AjaxRequestTarget target) {
            doClick(nodeid, target);
        }
    }
}
