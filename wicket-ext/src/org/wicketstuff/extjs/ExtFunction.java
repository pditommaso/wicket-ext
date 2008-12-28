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

import org.apache.wicket.util.string.Strings;

/**
 * Wrapper class to handle an anonymous JavaScript function
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtFunction implements Serializable {

	private String body;
	private String arguments;

	public ExtFunction(String body) {
		this.body = body;
	}

	public ExtFunction(String arguments, String body ) {
		this.arguments = arguments;
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("function");
		result.append("(");
		if( !Strings.isEmpty(arguments) ) {
			result.append(arguments);
		}
		result.append(")");

		result.append("{ ") .append(body) .append(" }");
		return result.toString();
	}
}
