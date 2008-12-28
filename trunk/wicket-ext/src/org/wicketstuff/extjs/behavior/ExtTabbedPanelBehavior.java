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

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Component.IVisitor;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.repeater.AbstractRepeater;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;

public class ExtTabbedPanelBehavior extends ExtComponentBehavior {

  private boolean autoTagComponents;

  public ExtTabbedPanelBehavior(Config config) {
    super("Ext.TabPanel", config);
  }

  public ExtTabbedPanelBehavior setAutoTagComponents( boolean autoTag ) {
	  autoTagComponents = autoTag;
	  return this;
  }

  @Override
  public void beforeRender(Component component) {
    super.beforeRender(component);

    if (autoTagComponents) {
      ((MarkupContainer)component).visitChildren(MarkupContainer.class, new IVisitor(){

        public Object component(Component child) {
          if (child instanceof AbstractRepeater) {
            return null;
          }
          child.setOutputMarkupId(true);
          child.add(new AttributeAppender("class", new Model("x-tab"), " ") {
            @Override
            public boolean isTemporary() {
              return true;
            }
          });

          return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
        }});
    }
  }

  @Override
  protected void onExtConfig(Config options) {
	  options.set("autoTabs",true);
	  options.set("autoWidth", true);
	  options.set("deferredRender", false);
	  options.set("activeTab", 0); // <-- this value could be defined programmatically
  }



}
