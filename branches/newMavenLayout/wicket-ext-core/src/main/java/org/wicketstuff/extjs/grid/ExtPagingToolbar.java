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

package org.wicketstuff.extjs.grid;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.data.ExtDataLink;

public class ExtPagingToolbar extends ExtClass {


	private static Config defaultOptions = new Config();

	{
		defaultOptions.put("pageSize", 25);
		defaultOptions.put("displayInfo", true);
		defaultOptions.put("displayMsg","Displaying topics {0} - {1} of {2}");
		defaultOptions.put("emptyMsg", "No topics to display");
	}

	private ExtDataLink link;

	public ExtPagingToolbar(ExtDataLink link) {
		this(link, new Config());
	}

	public ExtPagingToolbar(ExtDataLink link, Config config) {
		super("Ext.PagingToolbar",config);
		this.link = link;
	}

	@Override
	public CharSequence newInstance() {
		config().putIfNotExists(defaultOptions);
		config().put("store", link.getStore() );
		return super.newInstance();
	}


	public void setPageSize( int pageSize ) {
		config().set("pageSize", pageSize);
	}

}
