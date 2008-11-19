package org.wicketstuff.extjs.behavior;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtClass;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.XTemplate;
import org.wicketstuff.extjs.data.ExtDataSource;
import org.wicketstuff.extjs.util.WicketCallBuilder;

public abstract class ExtAutoCompleteBehavior extends ExtFieldBehavior {


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

	private ExtDataSource data;
	
	private XTemplate template;
		
	public ExtAutoCompleteBehavior() {
			super("Ext.form.ComboBox");
	}

	public ExtAutoCompleteBehavior(Config options) {
		super("Ext.form.ComboBox", options);
	}
	
	
	@Override
	public void onBind() { 
		super.onBind();
		//getComponent().add( HeaderContributor.forJavaScript(ExtAutoCompleteBehavior.class, "autocomplete.js") );
	}

	/* 
	 * This ajax behaviour handle the drop-down selection change
	 */
	@Override
	public void onRequest() {
		String key = RequestCycle.get().getRequest().getParameter(QUERY_PARAM);

		AjaxRequestTarget target = new AjaxRequestTarget(getComponent().getPage());
		RequestCycle.get().setRequestTarget(target);
		onSelect( target, key );
	}
	
	protected abstract void onSelect(AjaxRequestTarget target, String key);


	@Override
	public ExtClass create( Config config ) { 
		String url = getCallbackUrl().toString();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put(QUERY_PARAM, Ext.literal("record.id") );
//		String ajax = ExtAjax.request( url, params );
		WicketCallBuilder ajax = new WicketCallBuilder(url);
		ajax.append(params);
		
		
		Config options = new Config()
			.set("displayField", "display")
			.set("typeAhead", false)
			.set("loadingText", "Loading...")
			.set("forceSelection", true) // True to restrict the selected value to one of the values in the list, false to allow the user to set arbitrary text into the field (defaults to false) 
			.set("selectOnFocus",true)	 // True to select any existing text in the field immediately on focus. Only applies when editable = true (defaults to false) 	
			.set("pageSize", 0)			 // If greater than 0, a paging toolbar is displayed in the footer of the dropdown list and the filter queries will execute with page start and limit parameters. Only applies when mode = 'remote' (defaults to 0) 
			.set("minChars", 1)
			.set("hideTrigger", false)
			.set("itemSelector","div.search-item")
			.set("onSelect", new ExtFunction("record,index", ON_SELECT_DEFAULT + ajax ) );

		if( data != null ) { 
			options.set("store", data.getStore() );
			options.set("queryParam", data.getQueryParam());
		}
		
		if( template != null ) { 
			options.set("tpl", getTemplate() );
		}
		
		options.putAll(config);
		return super.create(options);
	}
		
	public ExtDataSource getData() {
		return data;
	}
	
	public void setData(ExtDataSource data) {
		this.data = data;
	}
	
	public void setTemplate(XTemplate template) {
		this.template = template;
	}

	private XTemplate getTemplate() { 
		return template;
	}
}	

