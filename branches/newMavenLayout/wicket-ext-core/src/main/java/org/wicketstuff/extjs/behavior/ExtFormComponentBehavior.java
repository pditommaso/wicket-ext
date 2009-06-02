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
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.form.FormComponent;
import org.wicketstuff.extjs.Config;

/**
 * Base behavior for Wicket form component
 *
 * add required field behavior, suggest other ext formcomponent to extend this class
 *
 * @author Jackie Zhong
 *
 */
public abstract class ExtFormComponentBehavior extends ExtComponentBehavior {

    public ExtFormComponentBehavior(String classname) {
        super(classname);
    }

    public ExtFormComponentBehavior(String classname, Config options) {
        super(classname, options);
    }

    public FormComponent getFormComponent() {
        Component component = getComponent();
        if (component instanceof FormComponent)
            return (FormComponent) component;
        throw new WicketRuntimeException("This Behavior can only attach to a wicket form component.");
    }

    @Override
    protected void onExtConfig(Config config) {
        super.onExtConfig(config);
        if (getFormComponent().isRequired())
            config.set("allowBlank", false);
    }
}
