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


/** 
 * Apply the Ext TextArea component to a Wicket TextArea
 */
public class ExtTextAreaBehavior extends ExtComponentBehavior {

	public ExtTextAreaBehavior() { 
		super("Ext.form.TextArea");
	}

	public ExtTextAreaBehavior(Config options) { 
		super("Ext.form.TextArea", options);
	}
	
	/**
	 * @param grow <code>true</code> if this field should automatically grow and shrink to its content
	 */
	public ExtTextAreaBehavior setGrow( boolean grow ) { 
		config().set("grow", grow);
		return this;
	}
	
	/**
	 * @param rows The maximum height to allow when grow = true (defaults to 1000) 
	 */
	public ExtTextAreaBehavior setGrowMax( int heigth ) { 
		config().set("growMax", heigth);
		return this;
	}

	/**
	 * @param rows The minimum height to allow when grow = true (defaults to 60) 
	 */
	public ExtTextAreaBehavior setGrowMin( int height ) { 
		config().set("growMin", height);
		return this;
	}
}
