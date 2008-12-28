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

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.model.IModel;

/**
 * Ext helper class. Provides common utility methods
 *
 * @author Paolo Di Tommaso
 *
 */


public class Ext {

	private static ExtContributionFactory contributionFactory = new ExtContributionFactory();

	private static ExtContribution bundle;


	/**
	 * Static getter to access to the Ext bundle contribution signeton
	 */
	public static ExtContribution bundle() {
		if( bundle == null ) {
			bundle = contributionFactory.load();
		}
		return bundle;
	}


	/**
	 * Package accessible method to define a
	 * @param bundle
	 */
	public static void setBundleFactory( ExtContributionFactory factory ) {
		if( factory == null ) {
			throw new IllegalArgumentException("Argument 'factory' cannot be null");
		}
		Ext.contributionFactory = factory;
	}

	@Deprecated
	public static void addContribution( final Component component ) {
		IHeaderContributor[] contributions = bundle().getContributions();
		for( IHeaderContributor contrib : contributions ) {
			component.add( new HeaderContributor(contrib) );
		}
	}

	private static CharSequence escape(CharSequence input) {
		if (input == null) {
			return null;
		}
		StringBuilder result = new StringBuilder(64);
		for( int i=0, c=input.length(); i<c; i++ ) {
			char ch;
			switch( ch = input.charAt(i) ) {
				case '\\': result.append("\\\\"); break;
				case '\'': result.append("\\'"); break;
				case '\"': result.append("\\\""); break;
				case '\n': result.append("\\n"); break;
				case '\r': result.append("\\r"); break;
				case '\t': result.append("\\t"); break;
				default: result.append(ch);
			}
		}
		return result;
	}

	public static Object serialize(Object value) {
		if( value instanceof CharSequence ) {
			return "'" + escape((CharSequence)value) + "'";
		}
		else if( value instanceof Map<?,?> ){
			return serialize( (Map<?,?>) value );
		}
		else if( value instanceof Collection<?> ){
			return serialize( (Collection<?>) value );
		}
		else if( value != null && value.getClass().isArray() ) {
			return serialize( (Object[]) value );
		}
		else if( value instanceof IModel ) {
			return serialize( ((IModel)value).getObject() );
		}
		else {
			return String.valueOf(value);
		}
	}


	public static CharSequence serialize( Object[] items ) {
		StringBuilder result = new StringBuilder();
		result.append("[");
		result.append( serialize(Arrays.asList(items) ));
		result.append("]");

		return result;
	}

	public static CharSequence serialize( Collection<?> items ) {
		if( items == null ) { return ""; }

		StringBuilder result = new StringBuilder();
		int c=0;
		for( Object o : items  ) {
			if( c++ > 0 ) { result.append(", "); }
			result.append(Ext.serialize(o));
		}

		return result;
	}

	public static CharSequence serialize( Map<?,?> map ) {
		StringBuilder result = new StringBuilder();
		result.append("{");
		int c = 0;
		for( Map.Entry<?,?> entry : map.entrySet() ) {
			if( c++ > 0 ) { result.append(", "); }
			result.append(entry.getKey()).append(": ").append( Ext.serialize(entry.getValue()) );

		}
		result.append("}");
		return result;

	}

	public static ExtLiteral literal( String literal ) {
		return new ExtLiteral(literal);
	}


	/**
	 * Parse a string vakeu returing a typed object. Supported types are:
	 * <li><code>Boolean</code></li>
	 * <li><code>Integer</code></li>
	 * <li><code>Double</code></li>
	 * <li><code>String</code></li>
	 *
	 * @param value the string value to be converted
	 * @return a typed object for not <i>null</i> input value or <code>null</code> otherwise
	 */
	public static Object parseValue( String value ) {
		if( value == null ) { return null; }
		Object result;
		result = boolValue(value);
		if( result != null ) return result;

		result = intValue(value);
		if( result != null ) return result;

		result = doubleValue(value);
		if( result != null ) return result;

		return value;
	}

	private static Boolean boolValue( String value ) {
		return "true".equals(value) ? Boolean.TRUE : ("false".equals(value) ? Boolean.FALSE: null );
	}

	private static Integer intValue( String value ) {
		try {
			return Integer.parseInt(value);
		} catch( NumberFormatException e ) {
			return null;
		}
	}

	private static Double doubleValue( String value ) {
		try {
			return Double.parseDouble(value);
		} catch( NumberFormatException e ) {
			return null;
		}
	}

}
