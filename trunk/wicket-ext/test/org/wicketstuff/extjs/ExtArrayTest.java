package org.wicketstuff.extjs;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ExtArrayTest {

	@Test
	public void createEmpty() { 
		ExtArray clazz = new ExtArray();
		assertEquals( clazz.toString() , "[]");
	}

	@Test
	public void createItems() { 
		ExtArray clazz = new ExtArray( true, 123, "alpha" );
		assertEquals( clazz.toString() , "[true, 123, 'alpha']");
	}
	
}
