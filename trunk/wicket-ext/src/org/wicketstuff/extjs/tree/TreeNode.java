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
