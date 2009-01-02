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

import java.util.Iterator;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.behavior.IBehavior;
import org.wicketstuff.extjs.Config;

public abstract class ExtAsyncTreePanel extends ExtTreePanel {

	private static final long serialVersionUID = 1L;
	private ExtTreeDataLinkBehavior data;
	
	public ExtAsyncTreePanel(String id,String text) {
		this(id, new ExtTreeNode(text).setLeaf(false));
	}
	
	public ExtAsyncTreePanel(String id, ExtTreeNode root) {
		super(id, root.setLeaf(false));
		init();
    }

	public abstract Iterator<ExtTreeNode> iterator(ExtTreeNode tree);

	@Override
	public ExtTreePanel setRoot(ExtTreeNode root) {
		throw new WicketRuntimeException("Root node is allready setted.");
	}
	
	private void init() {
		add( (IBehavior)(data = new ExtTreeDataLinkBehavior(this)) );
    }

		
	@Override
	protected CharSequence getExtScript(Config config) { 
		StringBuilder result = new StringBuilder();

		ExtTreeLoader loader = new ExtTreeLoader(data.getCallbackUrl().toString());
		result.append( loader.newInstance("loader") );
		
		config.set("loader", loader );
		
		return result;
	}

}
