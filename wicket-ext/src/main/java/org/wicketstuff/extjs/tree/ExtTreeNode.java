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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;

/**
 * another simple version of ExtTreePanel/ExtTreeNode/ExtTreePanelBehavior
 *
 * support click event for the tree and the node.
 *
 * @author Jackie Zhong
 */
public class ExtTreeNode implements Serializable {

    private static final long serialVersionUID = 1L;
	private String text;
    private boolean enabled = true;
    private String cls;
    private String qtip;
    private boolean draggable = false;
    private boolean allowChildren = true;
    private ExtTreeNode parentNode;
    private List<ExtTreeNode> childNodes = new ArrayList<ExtTreeNode>();
    private boolean leaf = true;
    private String id = null;

    public ExtTreeNode() {
        childNodes = new ArrayList<ExtTreeNode>();
    }

    public ExtTreeNode(String text) {
        this();
        this.text = text;
    }
      
    public ExtTreeNode(String text, ExtTreeNode parent) {
        this();
        this.text = text;
        setParentNode(parent);
    }

    public String getId() {
    	if(id!=null)
    		return id;
    	else{
	        if (isRoot())
	            return "0";
	        return parentNode.getId() + "_" + parentNode.childNodes().indexOf(this);
    	}
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getText() {
        return text;
    }

    public ExtTreeNode setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ExtTreeNode setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getCls() {
        return cls;
    }

    public ExtTreeNode setCls(String cls) {
        this.cls = cls;
        return this;
    }

    public String getQtip() {
        return qtip;
    }

    public ExtTreeNode setQtip(String qtip) {
        this.qtip = qtip;
        return this;
    }

	public boolean isLeaf() {
		return leaf;
	}

	public ExtTreeNode setLeaf(boolean leaf) {
		this.leaf = leaf;
        return this;
	}
    
    public boolean isDraggable() {
        return draggable;
    }

    public ExtTreeNode setDraggable(boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public boolean isAllowChildren() {
        return allowChildren;
    }

    public ExtTreeNode setAllowChildren(boolean allowChildren) {
        this.allowChildren = allowChildren;
        return this;
    }

    public ExtTreeNode getRoot() {
        ExtTreeNode root = this;
        while (root.getParentNode() != null)
            root = root.getParentNode();
        return root;
    }

    public boolean isRoot() {
        return parentNode == null;
    }

    public ExtTreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(ExtTreeNode parentNode) {
        if (this.parentNode != null && this.parentNode != parentNode)
            this.parentNode.removeChild(this);
        if (!parentNode.contains(this))
            parentNode.appendChild(this);
        this.parentNode = parentNode;
    }

    public List<ExtTreeNode> childNodes() {
        return childNodes;
    }

    public boolean contains(ExtTreeNode node) {
        return childNodes.contains(node);
    }
    
    public void appendChild(ExtTreeNode node) {
        if (childNodes.indexOf(node) == -1)
            childNodes.add(node);
        if (node.getParentNode() != this) {
            node.setParentNode(this);
        }
    }

    public void removeChild(ExtTreeNode node) {
        node.setParentNode(null);
        childNodes.remove(node);
    }

    protected void onClick(AjaxRequestTarget target) {
    }

    private Config initConfig() {
        Config config = new Config();
        config.set("id", getId());
        if (text != null)
            config.set("text", text);
        config.set("enabled", enabled);
        if (cls != null)
            config.set("cls", cls);
        if (qtip != null)
            config.set("qtip", qtip);
        config.set("draggable", draggable);
        config.set("allowChildren", allowChildren);
        if(childNodes.size() > 0)
        	config.set("leaf", false);
        else
        	config.set("leaf", leaf);
        if (childNodes.size() > 0)
            config.set("children", childNodes.toArray());
        return config;
    }

    @Override
	public String toString() {
        Config config = initConfig();
        StringBuilder result = new StringBuilder();
        if (isRoot()) {
            result.append("new Ext.tree.AsyncTreeNode").append("(");
            result.append(Ext.serialize(config));
            result.append(")");
        }
        else
            result.append(Ext.serialize(config));

        return result.toString();
    }


}
