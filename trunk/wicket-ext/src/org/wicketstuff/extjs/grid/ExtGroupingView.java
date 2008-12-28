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
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.data.IDataSource;

/**
 * Render a <code>Ext.grid.GridPanel</code> component. This component behaves also as a data provider
 *
 * @author Paolo Di Tommaso
 *
 * @param <T> the object type displayed in the grid
 *
 * @see {@link IDataSource}
 */
public abstract class ExtGroupingView<T> extends ExtAbstractGrid<T> {

	private static final long serialVersionUID = 1L;

	public ExtGroupingView(String id) {
		super(id);
		init();
	}

	private void init() {
		/*
		 * The grid behavior
		 */
		add( new ExtGridGroupingBehavior(config()) );

	}

	public ExtGroupingView<T> setGroupField( String field ) {
		getDataLink().setGroupField(field);
		getDataLink().setSortField(field);
		getDataLink().setSortDirection("ASC");
		return this;
	}

	public String getGroupField() {
		return getDataLink().getGroupField();
	}

	/**
	 * The Ext GridPanel behavior
	 *
	 * @author Paolo Di Tommaso
	 *
	 */
	class ExtGridGroupingBehavior extends ExtComponentBehavior {

		private static final long serialVersionUID = 1L;

		public ExtGridGroupingBehavior(Config config) {
			super("Ext.grid.GridPanel",config);
		}

		@Override
		protected String getApplyMethod() {
			return "renderTo";
		}

		@Override
		protected CharSequence onExtScript(Config config) {
			StringBuilder result = new StringBuilder();
			/* render the store script */
			result.append( getDataLink().getStore().newInstance("store") );

			Config loadParams = new Config();
			if( getPageSize() > 0 ) {
				/* create the paging toolbar */
				ExtPagingToolbar pager = new ExtPagingToolbar(getDataLink());
				pager.setPageSize(getPageSize());
				result.append( pager.newInstance("paging") );
				/* add the pager to the grid config */
				config.set("bbar", pager);
				/* load params */
				loadParams.set("start",0);
				loadParams.set("limit",getPageSize());
			}

			GroupingView view = new GroupingView();
			result.append( view.newInstance("view") );
			/* add the view to the grid config */
			config.set("view", view);

			/* configure the grid and render its script */
			config.set("store", getDataLink().getStore() );
			config.set("columns", getColumns());
			result.append( create(config).newInstance("grid") );

			/* load the data */
			result.append(String.format("grid.getStore().load({params:%s})", loadParams));
			return result;
		}
	};
}
