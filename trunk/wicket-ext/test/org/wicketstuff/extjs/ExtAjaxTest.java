package org.wicketstuff.extjs;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ExtAjaxTest {

	
	@Test 
	public void request() { 
		assertEquals( ExtAjax.request( new Config() ), "Ext.Ajax.request({})");
		assertEquals( ExtAjax.request( new Config("p",1) ), "Ext.Ajax.request({p: 1})");
	}

	public void requestWithUrl() { 
		assertEquals( ExtAjax.request("/the/url"), "Ext.Ajax.request({url: '/the/url'})");
	}

	
}
