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

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.XTemplate;

/**
 * Wrapper for <code>Ext.grid.RowExpander</code> JavaScript class
 *
 * @author Paolo Di Tommaso
 *
 */
public class RowExpander extends ExtClass {

	public RowExpander( ) {
		super("Ext.grid.RowExpander");
	}

	public RowExpander(Config config) {
		this();
	}

	public RowExpander(XTemplate template) {
		this();
		config().set("tpl", template);
	}
}
