package com.test.wicket.ext;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class GalleryApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return IndexPage.class;
	}

	
	@Override
	public void init() { 
		super.init();
		getMarkupSettings().setStripWicketTags(true);
	}
}
