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

package org.wicketstuff.extjs.ux.behavior;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.behavior.ExtAbstractBehavior;


/**
 * Applying this behavior to a component container for an HTML table will render it as Ext grid
 * <P>
 * For example:
 * <p>
 * <pre>HTML
 *
 * &lt;table wicket:id="table-1"&gt;
 * &lt;thead&gt;
 * &lt;tr&gt;&lt;td&gt;Column 1&lt;/td&gt;&lt;td&gt;Column 2&lt;/td&gt;&lt;td&gt;Column 3&lt;/td&gt;&lt;/tr&gt;
 * &lt;thead&gt;
 * &lt;tbody&gt;
 * &lt;tr&gt;&lt;td&gt;A&lt;/td&gt;&lt;td&gt;B&lt;/td&gt;&lt;td&gt;C&lt;/td&gt;&lt;/tr&gt;
 * &lt;tr&gt;&lt;td&gt;..&lt;/td&gt;&lt;td&gt;..&lt;/td&gt;&lt;td&gt;..&lt;/td&gt;&lt;/tr&gt;
 * &lt;/tbody&gt;
 * &lt;/table&gt;
 * </pre>
 *
 * <pre>JAVA
 *
 * WebMarkupContainer table = new WebMarkupContainer("table-1").add( new ExtTableGridBehavior() );
 * add( table );
 * </pre>
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtTableGridBehavior extends ExtAbstractBehavior {

	private static ResourceReference TABLEGRID = new ResourceReference(ExtTableGridBehavior.class,"tablegrid.js");

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavascriptReference(TABLEGRID);
	}

	@Override
	public CharSequence renderExtScript() {
		Config options = new Config("stripeRows",true);
		String id = getComponent().getMarkupId();
		ExtClass grid = new ExtClass("Ext.grid.TableGrid", id, options);
		return String.format("%s.render();", grid);
	}


}
