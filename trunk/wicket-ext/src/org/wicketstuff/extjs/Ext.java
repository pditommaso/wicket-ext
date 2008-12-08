package org.wicketstuff.extjs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.model.IModel;

/**
 * Ext helper class. Provides common utility methods
 * 
 * @author Paolo Di Tommaso
 *
 */
public class Ext {

	public interface Js {
		ResourceReference EXT_ALL = new ResourceReference(Ext.class,"ext-all.js");
		ResourceReference EXT_ALL_DEBUG = new ResourceReference(Ext.class,"ext-all-debug.js");
		ResourceReference EXT_BASE = new ResourceReference(Ext.class,"adapter/ext/ext-base.js");
	}
	
	public interface Images { 
		ResourceReference BLANK = new ResourceReference(Ext.class,"s.gif");
	}
	
	public interface Css {
		ResourceReference EXT_ALL = new ResourceReference(Ext.class,"resources/css/ext-all.css");
	}
	
	public static void addContribution( final Component component ) { 
		component.add(HeaderContributor.forCss( Ext.Css.EXT_ALL ));
		component.add(HeaderContributor.forJavaScript( Ext.Js.EXT_BASE ));
		component.add(HeaderContributor.forJavaScript( Ext.Js.EXT_ALL_DEBUG ));		
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
	
}
