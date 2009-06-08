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
import org.wicketstuff.extjs.ExtContainer;
import org.wicketstuff.extjs.behavior.ExtDataLinkBehavior;
import org.wicketstuff.extjs.data.IDataSource;

/**
 * Abstract class for data containg components
 *
 * @author Paolo Di Tommaso
 *
 * @param <T>
 */
public abstract class ExtAbstractDataContainer<T> extends ExtContainer implements IDataSource<T> {

	private static final long serialVersionUID = 1L;

	private int pageSize = 0;

	private ExtDataLinkBehavior<T> data;

	public ExtAbstractDataContainer(String id, IModel model) {
		super(id, model);
		init();
	}

	public ExtAbstractDataContainer(String id) {
		super(id);
		init();
	}

	private void init() {
		/*
		 * data store
		 */
		add( data=new ExtDataLinkBehavior<T>(this) );
	}

	final protected ExtDataLinkBehavior<T> getDataLink() {
		return data;
	}

	public ExtAbstractDataContainer<T> setPageSize( int pageSize ) {
		this.pageSize = pageSize;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSortField() {
		return data.getSortField();
	}

	public ExtAbstractDataContainer<T> setSortField( String sortField ) {
		data.setSortField(sortField);
		return this;
	}

	public String getSortDirection() {
		return data.getSortDirection();
	}

	public ExtAbstractDataContainer<T> setSortDirection( String direction ) {
		data.setSortDirection(direction);
		return this;
	}


	public ExtAbstractDataContainer<T> setLoadMask( boolean loadMask ) {
		config().set("loadMask", loadMask );
		return this;
	}
}
