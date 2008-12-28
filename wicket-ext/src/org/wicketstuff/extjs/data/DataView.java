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

package org.wicketstuff.extjs.data;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;

/**
 * Wrapper class for <code>Ext.DataView</code> class
 *
 * @author Paolo Di Tommaso
 *
 */
public class DataView extends ExtClass {

	public DataView() {
		this(new Config());
	}

	public DataView(Config config) {
		super("Ext.DataView",config);
	}


}
