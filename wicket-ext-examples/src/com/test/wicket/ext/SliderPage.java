package com.test.wicket.ext;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;
import org.wicketstuff.extjs.slider.ExtSlider;

public class SliderPage extends WebPage {

	public SliderPage() { 
		
		ExtSlider slide1 = new ExtSlider("slider1"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onChange(int oldValue,int newValue, AjaxRequestTarget target) {
				target.appendJavascript("alert('Value changed from " + oldValue + " to " + newValue + "');");
			}			
		};
		add(slide1.setWidth(200));
		
		ExtSlider slide2 = new ExtSlider("slider2", new Model<Integer>(25)){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onChange(int oldValue,int newValue, AjaxRequestTarget target) {
				target.appendJavascript("alert('Value changed from " + oldValue + " to " + newValue + "');");
			}			
		};
		slide2.setIncrement(5);
		add(slide2.setWidth(200));
		
		ExtSlider slide3 = new ExtSlider("slider3"){
			private static final long serialVersionUID = 1L;

			@Override
			protected void onChange(int oldValue,int newValue, AjaxRequestTarget target) {
				target.appendJavascript("alert('Value changed from " + oldValue + " to " + newValue + "');");
			}			
		};
		slide3.setVertical(true);
		slide3.setHeight(140);
		add(slide3);
		
		
	}
}
