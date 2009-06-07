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

import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.ExtTemplate;
import org.wicketstuff.extjs.grid.ColumnMap;
import org.wicketstuff.extjs.grid.ColumnModel;
import org.wicketstuff.extjs.grid.ExtGridView;

public abstract class ExtExpandingGridView<T> extends ExtGridView<T> {

	public ExtExpandingGridView(String id) {
		super(id);
		add( HeaderContributor.forJavaScript(ExtExpandingGridView.class,"RowExpander.js") );
	}

	private ExtTemplate rowTemplate;

	private RowExpander rowExpander;

	public ExtGridView<T> setRowTemplate( ExtTemplate template ) {
		rowTemplate = template;
		rowExpander = new RowExpander(template.getXTemplate());
		return this;
	}

	public ExtTemplate getRowTemplate() {
		return rowTemplate;
	}

	@Override
	protected ExtClass getPlugins() {
		return rowExpander;
	}

	@Override
	protected ColumnModel getColumnModel() {
		ColumnMap[] cols = getColumns();

		if( rowExpander == null ) {
			return new ColumnModel(cols);
		}

		/* ifthe row expander is defined it must prepend the column list definition in the columns array */
		Object[] expCols = new Object[ cols.length+1 ];
		expCols[0] = rowExpander;
		for( int i=0; i<cols.length; i++ ) {
			expCols[i+1]= cols[i];
		}
		return new ColumnModel(expCols);
	}

	@Override
	public void onBeforeRender() {
		super.onBeforeRender();
		if( rowTemplate != null ) {
			return;
		}
		// othewise try to discover it visting the children component
		visitChildren(ExtTemplate.class, new IVisitor() {

			public Object component(Component component) {
				setRowTemplate((ExtTemplate) component);
				return STOP_TRAVERSAL;
			}} );
	}


}
