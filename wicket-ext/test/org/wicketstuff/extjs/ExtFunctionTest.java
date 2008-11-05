package org.wicketstuff.extjs;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ExtFunctionTest {


	@Test 
	public void testFunctionBody() { 
		ExtFunction function = new ExtFunction("f();");
		assertEquals( function.toString() , "function(){ f(); }");
	}

	@Test 
	public void testFunctionParamsWithBody() { 
		ExtFunction function = new ExtFunction("p1","f();");
		assertEquals( function.toString() , "function(p1){ f(); }");
	}
	
	@Test 
	public void testFunctionMultiParamsWithBody() { 
		ExtFunction function = new ExtFunction("p1,p2,'p3'","f();");
		assertEquals( function.toString() , "function(p1,p2,'p3'){ f(); }");
	}
	
}
