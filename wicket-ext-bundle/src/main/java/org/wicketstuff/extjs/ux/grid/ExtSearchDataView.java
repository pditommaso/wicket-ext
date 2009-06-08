/*
 *  Copyright (C) 2008 Paolo Di Tommaso
 *  paolo.ditommaso@gmail.com
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.wicketstuff.extjs.ux.grid;

import org.apache.wicket.behavior.HeaderContributor;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.grid.ExtDataView;

public abstract class ExtSearchDataView<T> extends ExtDataView<T> {

	public ExtSearchDataView(String id) {
		super(id);
		add( HeaderContributor.forJavaScript(ExtSearchDataView.class,"SearchField.js") );
	}

	@Override
	protected Object getTopBar() {
		/* the search input bar */
		ExtClass input = new ExtClass("Ext.app.SearchField");
		input.config().set("store", getDataLink().getStore());
		input.config().set("paramName", getDataLink().getParamQuery() );
		Object[] result = { "Search: ", " ", input };
		return result;
	}

}
