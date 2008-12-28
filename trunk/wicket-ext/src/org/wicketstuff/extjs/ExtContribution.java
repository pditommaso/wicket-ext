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

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;


/**
 * Abstract factory class to load Ext resources with a customizable strategy
 *
 * @author Paolo Di Tommaso
 *
 */
public interface ExtContribution {

	/** The resource reference to the <code>ext-all.js</code> javascript resource */
	ResourceReference EXT_ALL_JS();

	/** The resource reference to the <code>ext-all.css</code> style sheet resource */
	ResourceReference EXT_ALL_CSS();

	/** The resource reference to the <code>ext-all.js</code> javascript resource */
	ResourceReference EXT_BASE_JS();

	/** The resource reference to blank image gif required by Ext */
	ResourceReference EXT_BLANK();


	/**
	 * @return
	 * 		the arrays containing the header contributions required by Ext bundle
	 */
	IHeaderContributor[] getContributions();

}
