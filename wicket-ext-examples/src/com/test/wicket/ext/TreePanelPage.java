package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.tree.ExtAsyncTreeNode;
import org.wicketstuff.extjs.tree.ExtTreePanel;
import org.wicketstuff.extjs.tree.TreeNode;

public class TreePanelPage extends WebPage {

	public TreePanelPage() { 
		
		add( new ExtTreePanel ("tree","Racine") {

			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<TreeNode> iterator(String id) {
				ArrayList<TreeNode> oList = new ArrayList<TreeNode>();
				if(!id.equals(ExtAsyncTreeNode.ROOT_ID)){
					oList.add(new TreeNode("1"+id,"Text 1"+id));
					oList.add(new TreeNode("2"+id,"Text 2"+id).setLeaf(true));
					oList.add(new TreeNode("2"+id,"Text 2"+id));
				}
				else{
					oList.add(new TreeNode("1","Text 1"));
					oList.add(new TreeNode("2","Text 2").setLeaf(true));
					oList.add(new TreeNode("2","Text 2"));
				}
				return oList.iterator();
			}			
			
		}.setTitle("Tree Panel Title").setAutoScroll(true));
	}
}
