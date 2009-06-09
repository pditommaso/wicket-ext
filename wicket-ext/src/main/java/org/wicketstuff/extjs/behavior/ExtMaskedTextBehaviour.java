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

package org.wicketstuff.extjs.behavior;

import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.ExtClass;


/**
 *
 * Enable regexp masking on textfield
 *
 * @author Matteo Fiandesio
 *
 */


public class ExtMaskedTextBehaviour extends ExtComponentBehavior {

	private static final long serialVersionUID = 1L;

	String mask;

	public ExtMaskedTextBehaviour(String regExpMask){
		super("Ext.form.TextField");
		this.mask = regExpMask;
	}


	@Override
	protected void onExtConfig( Config options ) {
		options.set("maskRe",new ExtClass("RegExp",mask));
	}

}
