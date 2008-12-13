/*
 * 中山市网上审批系统 CyberWorks
 * 信息交换平台系统 CyberExchange
 * 版权所有(C)：深圳市科健信息技术有限公司
 *            中山市人民政府
 * @作者   Jackie Zhong
 * 创建日期 2008-12-14
 *
 */
package org.wicketstuff.extjs.tree;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.string.StringList;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.behavior.ExtTreePanelBehavior;

/**
 * another simple version of ExtTreePanel/ExtTreeNode/ExtTreePanelBehavior
 * 
 * support click event for the tree and the node. 
 * 
 * @author Jackie Zhong
 */
public class ExtTreeList extends WebMarkupContainer {

    private ExtTreeNode root;

    private String title;

    private int width = 0;

    private int height = 0;

    private boolean rootVisible = true;

    private boolean autoScroll = true;

    private boolean border = true;

    public ExtTreeList(String id) {
        super(id);
        init();
    }

    public ExtTreeList(String id, ExtTreeNode root) {
        this(id);
        this.root = root;
    }

    public ExtTreeList setRoot(ExtTreeNode root) {
        this.root = root;
        return this;
    }

    public ExtTreeNode getRoot() {
        return root;
    }

    public String getTitle() {
        return title;
    }

    public ExtTreeList setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public ExtTreeList setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ExtTreeList setHeight(int height) {
        this.height = height;
        return this;
    }

    public boolean isAutoScroll() {
        return autoScroll;
    }

    public ExtTreeList setAutoScroll(boolean autoScroll) {
        this.autoScroll = autoScroll;
        return this;
    }

    public boolean isBorder() {
        return border;
    }

    public ExtTreeList setBorder(boolean border) {
        this.border = border;
        return this;
    }

    public boolean isRootVisible() {
        return rootVisible;
    }

    public ExtTreeList setRootVisible(boolean rootVisible) {
        this.rootVisible = rootVisible;
        return this;
    }

    private void init() {
        setOutputMarkupId(true);
        add(new ExtTreePanelBehavior() {
            @Override
            protected void onExtConfig(Config options) {
                super.onExtConfig(options);
                if (root == null)
                    throw new WicketRuntimeException("root node must be setted.");
                options.set("root", root);
                
                if (title != null) {
                    options.set("title", title);
                    options.set("collapsible", true);
                }
                if (width > 0)
                    options.set("width", width);
                else
                    options.set("width", "auto");
                if (height > 0)
                    options.set("height", height);
                else
                    options.set("height", "auto");
                options.set("autoScroll", autoScroll);
                options.set("border", border);
                options.set("rootVisible", rootVisible);
            }
            @Override
            protected void onClick(String nodeid, AjaxRequestTarget target) {
                doClick(nodeid, target);
            }
        });
    }

    private void doClick(String nodeid, AjaxRequestTarget target) {
        StringList list = StringList.tokenize(nodeid, "_");
        ExtTreeNode node = root;
        for (int i = 1; i < list.size(); i++)
            node = node.childNodes().get(Integer.valueOf(list.get(i)));

        onClick(node, target);
        node.onClick(target);
    }

    protected void onClick(ExtTreeNode node, AjaxRequestTarget target) {
    }
}
