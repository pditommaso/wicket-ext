package org.wicketstuff.extjs.grid;

import org.apache.wicket.util.string.Strings;
import org.wicketstuff.extjs.Config;

/**
 * Model object to be used with Grids. This object mimics <code>Ext.grid.ColumnModel</code>
 * 
 * @author Paolo Di Tommaso
 *
 */
public class ColumnModel extends Config {

	private String property;
	
	public ColumnModel(String dataIndex, String header ) {
		setDataIndex(dataIndex);
		setHeader(header);
	}
	
	private String val( Object val ) { 
		return val != null ? val.toString() : null;
	}
	
	public ColumnModel setDataIndex( String dataIndex ) { 
		set("dataIndex", dataIndex);
		return this;
	}
	
	public String getDataIndex() { 
		return val(get("dataIndex"));
	}
	
	public ColumnModel setAlign( String align ) { 
		set("align", align);
		return this;
	}
	
	public ColumnModel setCss( String css ) { 
		set("css", css);
		return this;
	}

	public ColumnModel  setFixed( boolean fixed ) { 
		set("fixed", fixed );
		return this;
	}
	
	public ColumnModel setHeader( String header ) { 
		set("header", header);
		return this;
	}
	
	public ColumnModel  setHidden( boolean hidden ) { 
		set("hidden", hidden );
		return this;
	}
	
	public ColumnModel  setHideable( boolean hideable ) { 
		set(" hideable", hideable );
		return this;
	}
	
	public ColumnModel setResizeable( boolean resizable) { 
		set("resizable", resizable);
		return this;
	}
	
	public ColumnModel setSortable( boolean  sortable) { 
		set("sortable",  sortable);
		return this;
	}

	public ColumnModel setTooltip( boolean tooltip) { 
		set("tooltip", tooltip);
		return this;
	}

	public ColumnModel setWidth( boolean width) { 
		set("width", width);
		return this;
	}
	
	public ColumnModel setProperty( String property ) { 
		this.property = property;
		return this;
	}
	
	public String getProperty() { 
		return !Strings.isEmpty(property) ? property : getDataIndex();
	}
}
