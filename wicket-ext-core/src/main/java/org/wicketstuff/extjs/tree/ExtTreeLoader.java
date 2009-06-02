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

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

public class ExtTreeLoader extends ExtClass {


	private static final long serialVersionUID = 1L;
	private static Config defaultOptions = new Config();

	{
		defaultOptions.put("draggable", false);
	}

	private Config config;

	public ExtTreeLoader(String url) {
		this(url,new Config());
	}

	public ExtTreeLoader(String url, Config config) {
		super("Ext.tree.TreeLoader",config);
		config.put("url", url);
		this.config = config;
	}

	@Override
	public CharSequence newInstance() {
		config.putIfNotExists(defaultOptions);
		return super.newInstance();
	}
}
