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

import org.apache.wicket.util.lang.PropertyResolver;

/**
 * Basinc implentation for {@link ObjectMapper}
 *
 * @author Paolo Di Tommaso
 *
 * @param <T>
 */
public class SimpleObjectMapper<T> implements ObjectMapper<T> {

	final String[] attributes;

	/**
	 * Create the mapper for the specified object attributes
	 * @param attributes the list on attributes name
	 */
	public SimpleObjectMapper( String ... attributes ) {
		this.attributes = attributes;
	}

	public ObjectMap mapObject(T object, Integer index) {
		ObjectMap result = new ObjectMap();
		for( String name : attributes ) {
			if( object != null ) {
				result.put( name, PropertyResolver.getValue(name, object) );
			}
			else {
				result.put(name, null);
			}
		}

		return result;
	}

}
