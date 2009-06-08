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

import org.apache.wicket.Component;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtTemplate;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.data.DataView;

/**
 * Data View component based con Ext.DataView component
 * <p>
 * It supports {@link ExtTemplate} auto-discover in embeded in the DataView markup as inner component i.e.
 * <p>
 * Markup:
 * <pre>
 * &lt;div wicket:id="data-view" &gt;
 * &lt;div wicket:id="tpl" &gt;
 *   &lt;tpl for="."&gt;
 *   &lt;div class="search-item"&gt;
 *      &lt;h3&gt;&lt;span&gt;{lastPost:date("M j, Y")}&lt;br /&gt;by {col1}&lt;/span&gt;&lt;/h3&gt;
 *      &lt;p&gt;{excerpt}&lt;/p&gt;
 *   &lt;/div&gt;&lt;/tpl&gt;
 *   &lt;/div&gt;
 * &lt;/div&gt;
 * </pre>
 *
 * Java code:
 * <pre>
 * ExtDataView dview = new ExtDataView("data-view") { .. };
 * dview.add( new ExtTemplate("tpl");
 * </pre>
 * @author Paolo Di Tommaso
 *
 * @param <T>
 */
public abstract class ExtDataView<T> extends ExtAbstractDataContainer<T> {

	private ExtTemplate template;

	public ExtDataView(String id) {
		super(id);

		init();
	}

	private void init() {
		/*
		 * default configuration
		 */
		config().set("autoScroll", true);

		/*
		 * The grid behavior
		 */
		add( new DataViewBehavior(config()));
	}

	@Override
	public void onBeforeRender() {
		super.onBeforeRender();
		if( template != null ) {
			return;
		}
		// othewise try to discover it visting the children component
		visitChildren(ExtTemplate.class, new IVisitor() {

			public Object component(Component component) {
				template = (ExtTemplate) component;
				return STOP_TRAVERSAL;
			}} );
	}

	public ExtTemplate getTemplate() {
		return template;
	}

	public void setTemplate( ExtTemplate template ) {
		this.template = template;
	}

	public ExtDataView<T> setAutoScroll(  boolean autoScroll ) {
		config().set("autoScroll", autoScroll);
		return this;
	}

	public boolean getAutoScroll() {
		Boolean result = config().get("autoScroll");
		return result != null ? result.booleanValue() : false;
	}

	protected Object getTopBar() {
		return null;
	}

	/*
	 * private behaviour for this DataView component
	 */
	class DataViewBehavior extends ExtComponentBehavior {
		public DataViewBehavior(Config config) {
			super("Ext.Panel",config);
		}


		@Override
		public CharSequence onExtScript(Config config) {

			StringBuilder result = new StringBuilder();

			result.append( getDataLink().getStore().newInstance("ds") );

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

			DataView dataView = new DataView();
			dataView.config().set("tpl", template.getXTemplate());
			dataView.config().set("store", getDataLink().getStore());
			dataView.config().set("itemSelector", template.getItemSelector() );

			config.set("tbar", getTopBar() );

			/* configuration for the main Panel component */
			config.set("items", dataView );
			result.append( create(config).newInstance("panel") );

			/* load the date */
			result.append("ds.load();");
			result.append(String.format("ds.load({params:%s})", loadParams));
			return result;
		}
	}
}
