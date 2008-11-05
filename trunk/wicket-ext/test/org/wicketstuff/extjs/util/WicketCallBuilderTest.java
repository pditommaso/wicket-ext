package org.wicketstuff.extjs.util;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class WicketCallBuilderTest {

	@Test
	public void create() { 
		WicketCallBuilder builder = new WicketCallBuilder( "/the/url" );
		assertEquals( builder.toString(), "wicketAjaxGet('/the/url',null,null);" );
	}


}
 