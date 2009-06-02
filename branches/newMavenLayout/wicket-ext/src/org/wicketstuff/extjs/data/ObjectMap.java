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

import java.util.HashMap;

/**
 * Handy class to to replace verbose code like <code>HashMap<String,Object>()</code>
 *
 * @author Paolo Di Tommaso
 *
 */
public class ObjectMap extends HashMap<String, Object> {

	/**
	 * Simple constructor
	 */
	public ObjectMap() {

	}

	/**
	 * Initialize the map putting an entry with <code>null</code> value for each attribute in the map
	 *
	 */
	public ObjectMap( String... attributes ) {
		for( String item : attributes ) {
			put(item,null);
		}
	}

}
