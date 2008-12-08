package org.wicketstuff.extjs.behavior;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.template.TextTemplateHeaderContributor;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.XTemplate;
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

	private ExtDataLink link;
	
	private XTemplate template;
		
	
	{
		/* default component configuration */
		defaultOptions.set("typeAhead", false);
		defaultOptions.set("minChars", 1);
		defaultOptions.set("hideTrigger", true);
		defaultOptions.set("loadingText", "Loading...");
		defaultOptions.set("forceSelection", true);  // True to restrict the selected value to one of the values in the list, false to allow the user to set arbitrary text into the field (defaults to false) 
		defaultOptions.set("selectOnFocus",true);	 // True to select any existing text in the field immediately on focus. Only applies when editable = true (defaults to false) 	
		defaultOptions.set("pageSize", 0);			 // If greater than 0, a paging toolbar is displayed in the footer of the dropdown list and the filter queries will execute with page start and limit parameters. Only applies when mode = 'remote' (defaults to 0) 

	}
	
	public ExtAutoCompleteBehavior() {
			super("Ext.form.ComboBox");
	}

	public ExtAutoCompleteBehavior(Config options) {
		super("Ext.form.ComboBox", options);
	}
	
	@Override
	public void onBind() { 
		super.onBind();
		/* Fix  for component positioning on IE 
		 *	See https://extjs.com/forum/showthread.php?p=204826
		 */
		getComponent().add( TextTemplateHeaderContributor.forJavaScript(ExtAutoCompleteBehavior.class, "triggerfield_patch.js", new Model()) );
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


	@Override
	protected void onExtConfig( Config config ) { 
		String url = getCallbackUrl().toString();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put(QUERY_PARAM, Ext.literal("record.id") );
		WicketCallBuilder ajax = new WicketCallBuilder(url);
		ajax.append(params);
		
		
		config.set("displayField", "display");
		config.set("itemSelector","div.search-item");
		config.set("onSelect", new ExtFunction("record,index", ON_SELECT_DEFAULT + ajax ) );

		if( link != null ) { 
			config.set("store", link.getStore() );
			config.set("queryParam", link.getQueryParam());
		}
		
		if( template != null ) { 
			config.set("tpl", getTemplate() );
		}
		
	}
	
	public ExtDataLink getData() {
		return link;
	}
	
	public void setData(ExtDataLink link) {
		this.link = link;
	}
	
	public void setTemplate(XTemplate template) {
		this.template = template;
	}

	private XTemplate getTemplate() { 
		return template;
	}
}	

