package org.wicketstuff.extjs.grid;

import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.util.lang.PropertyResolver;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.behavior.ExtDataLinkBehavior;
import org.wicketstuff.extjs.data.DataProvider;
import org.wicketstuff.extjs.data.ExtDataLink;
import org.wicketstuff.extjs.data.ObjectMap;

/**
 * Render a <code>Ext.grid.GridPanel</code> component. This component behaves also as a data provider
 * 
 * @author Paolo Di Tommaso
 *
 * @param <T> the object type displayed in the grid
 * 
 * @see {@link DataProvider}
 */
public abstract class ExtGridPanel<T> extends WebMarkupContainer implements DataProvider<T> {
	
	
	private Config config ;
	
	{
		config = new Config();
		config.set("width", 540);
		config.set("height", 200);
	}
	
	
	private int pageSize = 25;
	
	private ExtDataLink data;
	
	public ExtGridPanel(String id) {
		super(id);
		
		init();
	}

	private void init() {
		
		/*
		 * data store
		 */
		add( (IBehavior)(data=new ExtDataLinkBehavior<T>(this)) );
		
		/*
		 * The grid behavior 
		 */
		add( new ExtGridBehavior(config) );
		
	}

	/**
	 * Default object mapping based on the meta-data provided by the columns model
	 * <p>
	 * Override this method to provide custom object mapping
	 */
	public ObjectMap mapObject(T object, Integer index) {
		ObjectMap result = new ObjectMap();
		ColumnMap[] cols = getColumns();
		for( ColumnMap col : cols ) { 
			if( object != null ) { 
				result.put( col.getDataIndex(), PropertyResolver.getValue(col.getProperty(), object) );
			}
			else { 
				result.put( col.getDataIndex(), null );
			}
		}
		return result;
	}
	
	/**
	 * Defines the list of columns the gird will display
	 * 
	 * @return the array of columns defined for this grid
	 * 
	 * @see {@link ColumnMap}
	 */
	protected abstract ColumnMap[] getColumns();
	
	public ExtGridPanel<T> setWidth( int width ) { 
		config.set("width", width );
		return this;
	}
	
	public ExtGridPanel<T> setHeight( int height ) { 
		config.set("height", height );
		return this;
	}

	
	public ExtGridPanel<T> setPageSize( int pageSize ) { 
		this.pageSize = pageSize;
		return this;
	}

	/**
	 * The Ext GridPanel behavior 
	 * 
	 * @author Paolo Di Tommaso
	 *
	 */
	class ExtGridBehavior extends ExtComponentBehavior { 
		
		public ExtGridBehavior(Config config) {
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
			if( pageSize > 0 ) { 
				/* create the paging toolbar */
				ExtPagingToolbar pager = new ExtPagingToolbar(data);
				pager.setPageSize(pageSize);
				result.append( pager.newInstance("paging") );
				/* add the pager to the grid config */
				config.set("bbar", pager);
				/* load params */
				params.set("start",0);
				params.set("limit",pageSize);
			}
			
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
