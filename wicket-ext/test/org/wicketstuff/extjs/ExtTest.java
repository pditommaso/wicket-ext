package org.wicketstuff.extjs;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ExtTest {

	@Test
	public void serializeEmptyArray() { 
		Object[] clazz = {};
		assertEquals( Ext.serialize(clazz).toString() , "[]");
	}

	@Test
	public void serielizeArray() { 
		Object[] clazz = new Object[] { true, 123, "alpha" };
		assertEquals( Ext.serialize(clazz).toString() , "[true, 123, 'alpha']");
	}

	
}
