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

import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.PropertyResolver;
import org.wicketstuff.extjs.data.ObjectMap;

public abstract class ExtAbstractGrid<T> extends ExtAbstractDataContainer<T> {

	public ExtAbstractGrid(String id) {
		super(id);
	}

	public ExtAbstractGrid(String id, IModel model) {
		super(id, model);
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

}
