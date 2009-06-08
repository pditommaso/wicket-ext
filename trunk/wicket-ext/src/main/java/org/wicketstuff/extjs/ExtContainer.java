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

package org.wicketstuff.extjs;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

/**
 * Base class for Ext based container
 *
 * @author Paolo Di Tommaso
 *
 */
public abstract class ExtContainer extends WebMarkupContainer {

	private Config config = new Config();

	public Config config() {
		return config;
	}


	public ExtContainer(String id) {
		super(id);
	}

	public ExtContainer(String id, IModel model) {
		super(id, model);
	}

	public ExtContainer setWidth( int width ) {
		config.set("width", width );
		return this;
	}

	public ExtContainer setHeight( int height ) {
		config.set("height", height );
		return this;
	}

	public ExtContainer setTitle( String title ) {
		config.set("title", title );
		return this;
	}

	public ExtContainer setCollapsible( boolean collapsible ) {
		config.set("collapsible", collapsible );
		return this;
	}

	public ExtContainer setAnimCollapse(boolean animCollapse ) {
		config.set("animCollapse", animCollapse );
		return this;
	}

	public ExtContainer setFrame(boolean frame ) {
		config.set("frame", frame );
		return this;
	}


}
