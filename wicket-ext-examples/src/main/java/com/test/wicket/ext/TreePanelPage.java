package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.tree.ExtAsyncTreePanel;
import org.wicketstuff.extjs.tree.ExtTreeNode;

public class TreePanelPage extends WebPage {

	public TreePanelPage() { 
		
		add( new ExtAsyncTreePanel ("tree","racine") {

			private static final long serialVersionUID = 1L;
			
			@Override
            protected void onClick(ExtTreeNode node, AjaxRequestTarget target) {
                target.appendJavascript("alert('tree.onClick: " + node.getText() + "');");
            }

			@Override
			public Iterator<ExtTreeNode> iterator(ExtTreeNode parent) {
				ArrayList<ExtTreeNode> oList = new ArrayList<ExtTreeNode>();
				
				oList.add(new ExtTreeNode("Text 1 ",parent).setLeaf(false));
				oList.add(new ExtTreeNode("Text 2 ",parent));
				oList.add(new ExtTreeNode("Text 3 ",parent).setLeaf(false));
				
				return oList.iterator();
			}			
			
		}.setAutoScroll(true).setTitle("Async Tree Panel").setWidth(200).setHeight(200));
	}
}
