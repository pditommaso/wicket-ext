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

package org.wicketstuff.extjs.tabs;

import org.apache.wicket.model.IModel;
import org.wicketstuff.extjs.ExtContainer;
import org.wicketstuff.extjs.behavior.ExtTabbedPanelBehavior;

public class ExtTabbedPanel extends ExtContainer {

	private boolean autoTagComponents;

	public ExtTabbedPanel(String id, boolean autoTagComponents) {
		super(id);
		this.autoTagComponents = autoTagComponents;
		init();
	}


  public ExtTabbedPanel(String id, IModel model, boolean autoTagComponents) {
    super(id, model);
	this.autoTagComponents = autoTagComponents;
	init();
  }


  private void init() {
    add(new ExtTabbedPanelBehavior(config()).setAutoTagComponents(autoTagComponents)) ;
  }
}
