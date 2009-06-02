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

import java.util.Date;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtDateFieldBehavior;

/**
 * Date field with Ext date chooser widget <code>Ext.form.DateField</code>
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtDateField extends TextField {

	public ExtDateField(String id) {
		super(id,Date.class);
		init();
	}

	public ExtDateField( String id, IModel model ) {
		super(id,model,Date.class);
		init();
	}

	private void init() {
		add( new ExtDateFieldBehavior() );
		add( new SimpleAttributeModifier("size", "12") );
		add( new SimpleAttributeModifier("maxlength", "10") );
	}

}
