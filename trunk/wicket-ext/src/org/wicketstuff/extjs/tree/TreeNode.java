package org.wicketstuff.extjs.tree;

import java.util.HashMap;
import java.util.Map;

import org.wicketstuff.extjs.Ext;

public class TreeNode {
	private String id;
	private String text;
	private String cls;
	private boolean leaf;
	
	public TreeNode(String pId, String pText){
		id = pId;
		text = pText;
		leaf = false;
	}
	
	public String getId() {
		return id;
	}
	
	public TreeNode setId(String id) {
		this.id = id;
		return this;
	}
	
	public String getText() {
		return text;
	}
	
	public TreeNode setText(String text) {
		this.text = text;
		return this;
	}
	
	public String getCls() {
		return cls;
	}
	
	public TreeNode setCls(String cls) {
		this.cls = cls;
		return this;
	}
	
	public boolean isLeaf() {
		return leaf;
	}

	public TreeNode setLeaf(boolean leaf) {
		this.leaf = leaf;
		return this;
	}

	@Override
	public String toString(){
		
		Map<String, Object> toto = new HashMap<String, Object>();
		toto.put("id", id);
		toto.put("text", text);
		toto.put("leaf", leaf);	
		if(cls!=null) toto.put("cls", cls);
		
		return Ext.serialize(toto).toString();		
	}
	
}
