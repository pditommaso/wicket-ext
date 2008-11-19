package org.wicketstuff.extjs.util;

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
