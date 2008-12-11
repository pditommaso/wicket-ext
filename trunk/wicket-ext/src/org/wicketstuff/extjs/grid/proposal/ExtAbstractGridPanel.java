package org.wicketstuff.extjs.grid.proposal;

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.PropertyResolver;
import org.wicketstuff.extjs.data.DataProvider;
import org.wicketstuff.extjs.data.ObjectMap;
import org.wicketstuff.extjs.grid.ColumnMap;

public abstract class ExtAbstractGridPanel<T> extends ExtAbstractPanel implements DataProvider<T> {

	private static final long serialVersionUID = 1L;

	{
		config.set("width", 540);
		config.set("height", 200);
	}
	
	public ExtAbstractGridPanel(String id, IModel model) {
		super(id, model);
		init();
	}

	public ExtAbstractGridPanel(String id) {
		super(id);
		init();
	}
	
	protected abstract void init();
	
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
	
	public ExtAbstractGridPanel<T> setLoadMask( boolean loadMask ) { 
		config.set("loadMask", loadMask );
		return this;
	}
}
