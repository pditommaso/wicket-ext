package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.List;

class SimpleBean {

	private String alpha;
	private String beta;
	private String gamma;

	public SimpleBean( String a1, String a2, String a3 ) {
		alpha = a1;
		beta = a2;
		gamma = a3;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getBeta() {
		return beta;
	}

	public void setBeta(String beta) {
		this.beta = beta;
	}

	public String getGamma() {
		return gamma;
	}

	public void setGamma(String gamma) {
		this.gamma = gamma;
	}

	public static List<SimpleBean> getData(int count){
		List<SimpleBean> result = new ArrayList<SimpleBean>();

		for( int i=0; i<count; i++ ) {
			result.add(new SimpleBean(String.valueOf(i), String.valueOf(Math.random()), String.valueOf( Math.round(Math.random()*10) ) ));
		}
		return result;
	}
}
