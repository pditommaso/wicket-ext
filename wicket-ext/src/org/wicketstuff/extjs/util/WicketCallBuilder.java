package org.wicketstuff.extjs.util;

/**
 * Wrapper to handle <code>wicketAjaxGet</code> function
 * 
 * @author Paolo Di Tommaso
 *
 */
public class WicketCallBuilder extends UrlBuilder {

	public WicketCallBuilder(CharSequence url) {
		super(url);
	}
	
	public WicketCallBuilder(String url) {
		super(url);
	}
	
	@Override
	public String toString() { 
		return String.format( "wicketAjaxGet(%s);", super.toString() );
	}
	
}
