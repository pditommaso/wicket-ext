/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketstuff.extjs;

import static org.testng.Assert.assertEquals;

import org.apache.wicket.util.tester.WicketTester;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExtClassTest {

	private WicketTester tester;

	@BeforeClass
	public void initialize() {
		tester = new WicketTester();
	}

	@Test(enabled=false)
	public void createEmpty() {
		ExtClass clazz = new ExtClass("Ext.Clazz");
		assertEquals( clazz.toString() , "new Ext.Clazz()");
	}


	@Test(enabled=false)
	public void createWithParam() {
		ExtClass clazz = new ExtClass("Ext.Clazz", "alpha");
		assertEquals( clazz.toString() , "new Ext.Clazz('alpha')");
	}

	@Test(enabled=false)
	public void createWithMultiParams() {
		ExtClass clazz = new ExtClass("Ext.Clazz", 123, true );
		assertEquals( clazz.toString() , "new Ext.Clazz(123, true)");
	}

	@Test
	public void createExtWindow() {
		Config options = new Config();
		ExtClass window = new ExtClass("Ext.Window", options);
		System.out.println( window.config() );
	}
}
