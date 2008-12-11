package org.wicketstuff.extjs.grid.proposal;

import org.apache.wicket.behavior.IBehavior;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.data.DataProvider;

/**
 * Render a <code>Ext.grid.GridPanel</code> component. This component behaves also as a data provider
 * 
 * @author Paolo Di Tommaso
 *
 * @param <T> the object type displayed in the grid
 * 
 * @see {@link DataProvider}
 */
public abstract class ExtGridGroupingPanel<T> extends ExtAbstractGridPanel<T> {
		
	private static final long serialVersionUID = 1L;
		
	private ExtDataGroupingLink data;
	
	public ExtGridGroupingPanel(String id) {
		super(id);
	}

	protected void init() {
		
		/*
		 * data store
		 */
		add( (IBehavior)(data=new ExtDataGroupingLinkBehavior<T>(this)) );
		
		/*
		 * The grid behavior 
		 */
		add( new ExtGridGroupingBehavior(config) );
		
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
			result.append( data.getStore().newInstance("store") );

			Config params = new Config();
			ExtGroupingView view = new ExtGroupingView();
			result.append( view.newInstance("view") );
			/* add the view to the grid config */
			config.set("view", view);
			
			/* configure the grid and render its script */
			config.set("store", data.getStore() );
			config.set("columns", getColumns());
			result.append( create(config).newInstance("grid") );
			
			/* load the data */
			result.append(String.format("grid.getStore().load({params:%s})", params));
			return result;
		}		
	};	
}
