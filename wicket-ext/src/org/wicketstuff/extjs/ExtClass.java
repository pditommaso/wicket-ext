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

/**
 * Wrapper class to handle a Ext class instance
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtClass implements Serializable {

	private String className;
	private Collection<Object> options;

	private String varName;
	private Config config;

	public ExtClass( String className ) {
		this(className, new Config());
	}


	public ExtClass( String className, Object... options ) {
		this.className = className;
		setOptions(options);
	}

	public ExtClass setOptions( Object[] options ) {
		this.options = Arrays.asList(options);
		config = (options.length>0 && options[0] instanceof Config) ? (Config)options[0] : null;
		return this;
	}

	public Config config() {
		return config ;
	}

	@Override
	public String toString() {
		if( varName != null ) {
			return varName;
		}

		return newInstance().toString();
	}

	public CharSequence newInstance() {
		StringBuilder result = new StringBuilder();
		result.append("new ") .append(className) .append("(");
		result.append(Ext.serialize(options));
		result.append(")");
		return result;
	}

	public CharSequence newInstance( String varName ) {
		this.varName = varName;
		return String.format("var %s=%s;", varName, newInstance());
	}

	public CharSequence newGlobalInstance( String varName ) {
		this.varName = varName;
		return String.format("%s=%s;", varName, newInstance());
	}

}
