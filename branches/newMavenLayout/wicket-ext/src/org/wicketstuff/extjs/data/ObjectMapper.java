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

import java.io.Serializable;
import java.util.Map;

/**
 * Translate an object instance to equivalent an {@link Map} representation
 *
 * @author Paolo Di Tommaso
 *
 */
public interface ObjectMapper<T> extends Serializable {

	/**
	 * Translate an object to an equivalent map.
	 * <p>
	 * Please note: when <code>object</code> parameter is <code>null</code> a map instance representing the expected object structure must be returned
	 *
	 * @param object the instance to translate
	 * @param index the index count if invoked in an iteration
	 * @return the mapped object
	 */
	ObjectMap mapObject( T object, Integer index  );

}
