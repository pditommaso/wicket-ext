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


public class XTemplate extends ExtClass {

	public XTemplate( String... template ) {
		super("Ext.XTemplate", (Object[])template);
	}

	public static XTemplate from( final String elemId ) {
		return new XTemplate() {
			@Override
			public CharSequence newInstance() {
				return String.format("Ext.XTemplate.from('%s')", elemId);
			}
		};
	}


}
