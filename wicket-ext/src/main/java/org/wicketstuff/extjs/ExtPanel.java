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

import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.behavior.ExtPanelBehavior;

/**
 * Component to wrap Ext panel
 *
 * @author Paolo Di Tommaso
 *
 */
public class ExtPanel extends ExtContainer {

	public ExtPanel(String id) {
		super(id);
		init();
	}

	public ExtPanel(String id, IModel model) {
		super(id, model);
		init();
	}

	private void init() {
		add(new ExtPanelBehavior(config()));
	}

}
