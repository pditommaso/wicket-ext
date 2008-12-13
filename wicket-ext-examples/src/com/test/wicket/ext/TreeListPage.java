package com.test.wicket.ext;

import org.apache.wicket.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.tree.ExtTreeList;
import org.wicketstuff.extjs.tree.ExtTreeNode;

public class TreeListPage extends WebPage {

    public TreeListPage(final PageParameters parameters) {
        super();
        
        final ExtTreeList tree = new ExtTreeList("tree") {
            @Override
            protected void onClick(ExtTreeNode node, AjaxRequestTarget target) {
                target.appendJavascript("alert('tree.onClick: " + node.getText() + "');");
            }
        };
        tree.setRoot(initTree());
        tree.setWidth(200);
        tree.setHeight(200);

        add(tree);
    }

    private ExtTreeNode initTree() {
        ExtTreeNode root = new ExtTreeNode("menu");

        ExtTreeNode item1 = new ExtTreeNode("item1");
        root.appendChild(item1);
        item1.appendChild(new ExtTreeNode("subitem11"));
        item1.appendChild(new ExtTreeNode("subitem12(click me)") {
            @Override
            protected void onClick(AjaxRequestTarget target) {
                target.appendJavascript("alert('node.onClick: " + this.getText() + "');");
            }
        });
        item1.appendChild(new ExtTreeNode("subitem13"));

        ExtTreeNode item2 = new ExtTreeNode("item2");
        ExtTreeNode subitem21 = new ExtTreeNode("subitem21");
        subitem21.appendChild(new ExtTreeNode("subitem211"));
        subitem21.appendChild(new ExtTreeNode("subitem212"));
        subitem21.appendChild(new ExtTreeNode("subitem213"));
        item2.appendChild(subitem21);
        item2.appendChild(new ExtTreeNode("subitem22"));
        item2.appendChild(new ExtTreeNode("subitem23"));

        root.appendChild(item2);
        root.appendChild(new ExtTreeNode("item3"));
        
        return root;
    }
}
