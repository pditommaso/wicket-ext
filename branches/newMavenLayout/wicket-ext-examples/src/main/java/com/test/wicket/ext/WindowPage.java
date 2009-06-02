package com.test.wicket.ext;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.wicketstuff.extjs.dialog.ExtWindow;

public class WindowPage extends WebPage {

	public WindowPage() {


		final ExtWindow window = new ExtWindow("window1");
		window.setModal(true);
		add(window);

		window.add( new Label("now", new AbstractReadOnlyModel() {
			@Override
			public Object getObject() {
				return System.currentTimeMillis();
			}} ));

		/* the button to show the window */
		add( new AjaxLink("button") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				window.show(target);
			} } );

	}

}
