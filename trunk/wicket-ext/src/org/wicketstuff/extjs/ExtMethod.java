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

package org.wicketstuff.extjs;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

public class ExtMethod implements Serializable {

	private String method;
	private Collection<Object> params;

	public ExtMethod( String method, Object ... params ) {
		this.method = method;
		this.params = Arrays.asList(params);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(method);
		result.append("(");
		result.append( Ext.serialize(params) );
		result.append(")");

		return result.toString();
	}

}
