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

import org.apache.wicket.util.string.Strings;
import org.wicketstuff.extjs.Config;

/**
 * Model object to be used with Grids. This object mimics <code>Ext.grid.ColumnModel</code>
 *
 * @author Paolo Di Tommaso
 *
 */
public class ColumnMap extends Config {

	private String property;

	public ColumnMap(String dataIndex, String header ) {
		setDataIndex(dataIndex);
		setHeader(header);
	}

	public ColumnMap(String dataIndex, String header, String beanPropery ) {
		setDataIndex(dataIndex);
		setHeader(header);
		setProperty(beanPropery);
	}

	private String val( Object val ) {
		return val != null ? val.toString() : null;
	}

	public ColumnMap setDataIndex( String dataIndex ) {
		set("dataIndex", dataIndex);
		return this;
	}

	public String getDataIndex() {
		return val(get("dataIndex"));
	}

	public ColumnMap setAlign( String align ) {
		set("align", align);
		return this;
	}

	public ColumnMap setCss( String css ) {
		set("css", css);
		return this;
	}

	public ColumnMap  setFixed( boolean fixed ) {
		set("fixed", fixed );
		return this;
	}

	public ColumnMap setHeader( String header ) {
		set("header", header);
		return this;
	}

	public ColumnMap  setHidden( boolean hidden ) {
		set("hidden", hidden );
		return this;
	}

	public ColumnMap  setHideable( boolean hideable ) {
		set(" hideable", hideable );
		return this;
	}

	public ColumnMap setResizeable( boolean resizable) {
		set("resizable", resizable);
		return this;
	}

	public ColumnMap setSortable( boolean  sortable) {
		set("sortable",  sortable);
		return this;
	}

	public ColumnMap setTooltip( String tooltip) {
		set("tooltip", tooltip);
		return this;
	}

	public ColumnMap setWidth( boolean width) {
		set("width", width);
		return this;
	}

	public ColumnMap setProperty( String property ) {
		this.property = property;
		return this;
	}

	public String getProperty() {
		return !Strings.isEmpty(property) ? property : getDataIndex();
	}
}
