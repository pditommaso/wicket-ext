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
