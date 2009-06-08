package com.test.wicket.ext;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.ExtPanel;

public class PanelPage extends WebPage {

	public PanelPage() {

		add( new ExtPanel("panel1")  );
		add( new ExtPanel("panel2")  );

	}

}
