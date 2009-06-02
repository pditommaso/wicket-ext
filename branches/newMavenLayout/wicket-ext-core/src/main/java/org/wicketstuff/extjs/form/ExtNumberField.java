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

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtNumberFieldBehavior;

public class ExtNumberField extends TextField {

	public ExtNumberField(String id) {
		super(id,Long.class);
		init();
	}

	public ExtNumberField(String id, Class<?> type) {
		super(id,type);
		init();
	}

	public ExtNumberField(String id, IModel model ) {
		super(id,model,Long.class);
		init();
	}

	public ExtNumberField(String id, IModel model, Class<?> type ) {
		super(id,model,type);
		init();
	}

	private void init() {
		add( new ExtNumberFieldBehavior() );
	}

}
