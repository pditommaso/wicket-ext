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

package org.wicketstuff.extjs.form;

import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtTextAreaBehavior;

/**
 * TextArea component integrating Ext wicket <code>Ext.form.TextArea</code>
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtTextArea extends TextArea {

	public ExtTextArea(String id) {
		super(id);
		init();
	}

	public ExtTextArea( String id, IModel model ) {
		super(id,model);
		init();
	}

	private void init() {
		add( new ExtTextAreaBehavior() );
	}

}
