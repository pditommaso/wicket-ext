package org.wicketstuff.extjs;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ExtClassTest {

	@Test
	public void createEmpty() { 
		ExtClass clazz = new ExtClass("Ext.Clazz");
		assertEquals( clazz.toString() , "new Ext.Clazz()");
	}
	

	@Test
	public void createWithParam() { 
		ExtClass clazz = new ExtClass("Ext.Clazz", "alpha");
		assertEquals( clazz.toString() , "new Ext.Clazz('alpha')");
	}

	@Test
	public void createWithMultiParams() { 
		ExtClass clazz = new ExtClass("Ext.Clazz", 123, true );
		assertEquals( clazz.toString() , "new Ext.Clazz(123, true)");
	}
	
}
