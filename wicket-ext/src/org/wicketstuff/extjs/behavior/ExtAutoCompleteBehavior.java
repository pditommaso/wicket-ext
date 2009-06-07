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

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.template.TextTemplateHeaderContributor;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ITemplate;
import org.wicketstuff.extjs.data.ExtDataLink;
import org.wicketstuff.extjs.util.WicketCallBuilder;

public abstract class ExtAutoCompleteBehavior extends ExtComponentBehavior {


	private static final String QUERY_PARAM = "key";
	
	private static final String ON_SELECT_DEFAULT = 
			"if(this.fireEvent('beforeselect', this, record, index) !== false){ "+
			"var curnt = this.getValue();"+
			"var value = record.data[this.valueField || this.displayField];" +
			"if (this.sep) { this.replaceActiveEntry(value); } " + 
			"else { this.setValue(value); }" +
			"this.collapse();"+
			"this.fireEvent('select', this, record, index);"+
			"if (curnt != this.getValue()){ this.fireEvent('change', this, this.getValue(), curnt); } "+
			"}; ";

	private boolean isMultiAutocomplete;
	
	public ExtAutoCompleteBehavior() {
			super("Ext.form.ComboBox");
	}

	public ExtAutoCompleteBehavior(Config options) {
		super("Ext.form.ComboBox", options);
	}
	
	public ExtAutoCompleteBehavior(Config options, boolean isMultiAutocomplete) { 
		super( isMultiAutocomplete ? "Ext.form.AutoComplete" : "Ext.form.ComboBox", options);
		this.isMultiAutocomplete = isMultiAutocomplete;
	}
	
	@Override
	public void onBind() { 
		super.onBind();
		if( isMultiAutocomplete ) { 
			getComponent().add( HeaderContributor.forJavaScript(ExtAutoCompleteBehavior.class, "autocomplete.js") );
		}
	}
	
	public ExtAutoCompleteBehavior setItemSelector( String itemSelector ) { 
		config().set("itemSelector", itemSelector);
		return this;
	}
	
	/** 
	 * This ajax behaviour handle the drop-down selection change
	 */
	@Override
	final public void onEvent(AjaxRequestTarget target) {
		String key = RequestCycle.get().getRequest().getParameter(QUERY_PARAM);
		onSelect( target, key );
	}
	
	protected abstract void onSelect(AjaxRequestTarget target, String key);

	protected abstract ITemplate getTemplate();

	protected abstract ExtDataLink getDataLink();

	@Override
	protected void onExtConfig( Config config ) { 
		super.onExtConfig(config);
		
		/* default properties */
		config.putIfNotExists("typeAhead", false);
		config.putIfNotExists("minChars", 1);
		config.putIfNotExists("hideTrigger", true);
		config.putIfNotExists("loadingText", "Loading...");
		config.putIfNotExists("forceSelection", true);   // True to restrict the selected value to one of the values in the list, false to allow the user to set arbitrary text into the field (defaults to false)
		config.putIfNotExists("selectOnFocus",true);	 // True to select any existing text in the field immediately on focus. Only applies when editable = true (defaults to false)
		config.putIfNotExists("pageSize", 0);			 // If greater than 0, a paging toolbar is displayed in the footer of the dropdown list and the filter queries will execute with page start and limit parameters. Only applies when mode = 'remote' (defaults to 0)
		config.putIfNotExists("displayField", "display");

		/* url callback and datastore binding configuration */
		String url = getCallbackUrl().toString();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put(QUERY_PARAM, Ext.literal("record.id") );
		WicketCallBuilder ajax = new WicketCallBuilder(url);
		ajax.append(params);
		
		config.set("onSelect", new ExtFunction("record,index", ON_SELECT_DEFAULT + ajax ) );

		ITemplate template = getTemplate();
		if( template != null ) {
			config.set("tpl", template.getXTemplate());
			config.set("itemSelector", template.getItemSelector());
		}

		ExtDataLink data = getDataLink();
		if( data != null ) {
			config.set("store", data.getStore() );
			config.set("queryParam", data.getParamQuery());
		}

	}
	
}
