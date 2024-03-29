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

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.model.Model;

public class ExtTabBehavior extends AbstractBehavior {

  private String title;

  public ExtTabBehavior() {
  }

  public ExtTabBehavior(String title) {
    this.title = title;
  }

  public void bind(Component component) {
      super.bind(component);
      component.setOutputMarkupId(true);
      component.add(new AttributeAppender("class", new Model("x-tab"), " "));
      if (title != null) {
          component.add(new AttributeModifier("title",true, new Model(title)));
      }
  }
}
