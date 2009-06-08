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

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtComboBoxBehavior;

/**
 * Basic drop down component integrating the Ext class <code>Ext.form.ComboBox</code>
 *
 */
public class ExtDropDownChoice extends DropDownChoice {

	public ExtDropDownChoice(String id) {
		super(id);
		init();
	}

	public ExtDropDownChoice(String id, IModel model) {
		super(id,model);
		init();
	}

	public ExtDropDownChoice(final String id, final List<?> choices) {
		super(id,choices);
		init();
	}

	public ExtDropDownChoice(final String id, final Object ... choices) {
		super(id,Arrays.asList(choices));
		init();
	}

	/* TODO add same constructors as parent class - paolo */


	private void init() {
		add( new ExtComboBoxBehavior() );
	}

}
