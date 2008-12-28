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

package org.wicketstuff.extjs.util;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
