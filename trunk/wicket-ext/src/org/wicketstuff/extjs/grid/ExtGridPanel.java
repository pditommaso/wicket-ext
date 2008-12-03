package org.wicketstuff.extjs.grid;

import java.util.Iterator;
import java.util.Map;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.behavior.ExtDataStoreBehavior;
import org.wicketstuff.extjs.util.ObjectMapper;

/**
 * Render a <code>Ext.grid.GridPanel</code> component
 * 
 * @author Paolo Di Tommaso
 *
 * @param <T> the object type displayed in the grid
 */
public abstract class ExtGridPanel<T> extends WebMarkupContainer {

	private Config config ;
	

	{
		config = new Config();
		config.set("width", 540);
		config.set("height", 200);
	}
	
	public ExtGridPanel(String id) {
		super(id);
		
		init();
	}

	private void init() {
		
		/* 
		 * the object mapper to translate entity to map items
		 */
		ObjectMapper<T> mapper = new ObjectMapper<T>() {

			public Map<String, Object> mapObject(T object, int index) {
				return ExtGridPanel.this.mapObject(object,index);
			}};
		
		/*
		 * data store
		 */
		final ExtDataStoreBehavior<T> data = new ExtDataStoreBehavior<T>(mapper) {

			@Override
			protected Iterator<T> getChoices(String input) {
				return onLoad();
			}};
			
		add( data );
		
		/*
		 * The grid behavior 
		 */
		ExtComponentBehavior grid = new ExtComponentBehavior("Ext.grid.GridPanel", config) { 
			@Override
			protected ExtClass create( Config config ) { 
				config.set("store", data.getStore() );
				config.set("columns", getColumns());
				return super.create(config);
			}
			
			@Override
			protected CharSequence onDomReady() { 
				StringBuilder result = new StringBuilder(super.onDomReady());
				result.append(".getStore().load()");
				return result;
			}
		} ;
		add(grid);
		
	}

	protected abstract Map<String, Object> mapObject(T object, int index);

	protected abstract Iterator<T> onLoad();

	protected abstract ColumnModel[] getColumns();
	
	public void setWidth( int width ) { 
		config.set("width", width );
	}
	
	public void setHeight( int height ) { 
		config.set("height", height );
	}
}
