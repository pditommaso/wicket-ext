package org.wicketstuff.extjs.slider;

import java.util.HashMap;
import java.util.Map;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.Config;
import org.wicketstuff.extjs.Ext;
import org.wicketstuff.extjs.ExtFunction;
import org.wicketstuff.extjs.ExtLiteral;
import org.wicketstuff.extjs.behavior.ExtComponentBehavior;
import org.wicketstuff.extjs.util.WicketCallBuilder;

public class ExtSlider extends WebMarkupContainer {

	private static final long serialVersionUID = 1L;
	private static final String VALUE_PARAM = "newValue";
	
	private Config config ;
	
	{
		config = new Config();
	}

	public ExtSlider(String id) {
		super(id, new Model(new Integer(0)));
		init();
	}

	public ExtSlider(String id, IModel model) {
		super(id, model);
		init();
	}
	
	private void init() {
		add( new ExtSliderBehavior(config) );		
	}
	
	public ExtSlider setWidth( int width ) { 
		config.set("width", width );
		return this;
	}
	
	public ExtSlider setHeight( int height ) { 
		config.set("height", height );
		return this;
	}
	
	public ExtSlider setMinValue( int minValue ) { 
		config.set("minValue", minValue );
		return this;
	}
	
	public ExtSlider setMaxValue( int maxValue ) { 
		config.set("maxValue", maxValue );
		return this;
	}
	
	public ExtSlider setIncrement( int increment ) { 
		config.set("increment", increment );
		return this;
	}
	
	public ExtSlider setVertical( boolean vertical ) { 
		config.set("vertical", vertical );
		return this;
	}
	
	public ExtSlider setValue( int value ) { 
		setModelObject(value);
		return this;
	}
	
	protected void onChange(int oldValue,int newValue, AjaxRequestTarget target) {
        
    }
	
	class ExtSliderBehavior extends ExtComponentBehavior { 
		
		private static final long serialVersionUID = 1L;
		
		public ExtSliderBehavior(Config config) {
			super("Ext.Slider",config);
		}

		@Override
		protected String getApplyMethod() { 
			return "renderTo";
		}
		
		@Override
		public void renderHead(IHeaderResponse response) {
	        super.renderHead(response);
	        response.renderJavascriptReference(WicketAjaxReference.INSTANCE);
		}
		
		@Override
		protected CharSequence onExtScript(Config config) { 
			config.set("value", new ExtLiteral(getModelObjectAsString()));
			return super.onExtScript(config);
		}
		
		@Override
	    protected void onExtConfig( Config config ) {
	        String url = getCallbackUrl().toString();
	        
	        Map<String,Object> params = new HashMap<String, Object>();
	        params.put(VALUE_PARAM, Ext.literal("newValue"));
	        WicketCallBuilder ajax = new WicketCallBuilder(url);
	        ajax.append(params);
	        config.set("listeners", new Config("changecomplete", new ExtFunction("slider,newValue", ajax.toString())));
	    }

		@Override
		protected final void onEvent(AjaxRequestTarget target) {
			String newValue = RequestCycle.get().getRequest().getParameter(VALUE_PARAM);
			String oldValue = getModelObjectAsString();
			setModelObject(Integer.valueOf(newValue));
			onChange(Integer.valueOf(oldValue),Integer.valueOf(newValue),target);
		}
	}
}
