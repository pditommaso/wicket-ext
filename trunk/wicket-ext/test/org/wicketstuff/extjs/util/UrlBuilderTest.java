package org.wicketstuff.extjs.util;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class UrlBuilderTest {

	@Test
	public void create() { 
		UrlBuilder builder = new UrlBuilder( "/the/url" );
		assertEquals( builder.toString(), "'/the/url'" );
	}

	@Test
	public void createWithParams() { 
		UrlBuilder builder = new UrlBuilder( "/the/url" )
			.append("p1","value1")
			.append("p2","value2");

		assertTrue( builder.toString().contains("+'&p1='+escape(value1)") );
		assertTrue( builder.toString().contains("+'&p2='+escape(value2)") );
	}

}
 