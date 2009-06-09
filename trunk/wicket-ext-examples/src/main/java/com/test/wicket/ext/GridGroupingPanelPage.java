package com.test.wicket.ext;

import java.util.Iterator;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.grid.ColumnMap;
import org.wicketstuff.extjs.grid.ExtGroupingView;

public class GridGroupingPanelPage extends WebPage {


	public GridGroupingPanelPage() {

		add( new ExtGroupingView<DataBean> ("grid") {

			private static final long serialVersionUID = 1L;

			@Override
			protected ColumnMap[] getColumns() {
				ColumnMap col1 = new ColumnMap("company", "Company name") .setSortable(true);
				ColumnMap col2 = new ColumnMap("price", "Price") .setSortable(true);
				ColumnMap col3 = new ColumnMap("change", "Change");
				ColumnMap col4 = new ColumnMap("lastUpdate", "Last update");
				ColumnMap col5 = new ColumnMap("industry", "Industry");

				return new ColumnMap[] { col1, col2, col3, col4, col5 };
			}

			public Integer totalRecords() { return DataHelper.list().size(); }

			public Iterator<DataBean> iterator(Query query) {
				Integer start = query.getStart() != null ? query.getStart() : 0;
				Integer count = query.getLimit() != null ? query.getLimit() : 100;
				return DataHelper.list(start, count, query.getSort(), query.getDirection()).iterator();

			}
		}.setGroupField("industry")
		 .setTitle("Grouping Panel")
		 .setFrame(true)
		 .setAnimCollapse(true)
		 .setCollapsible(true)
		 .setWidth(600)
		 .setHeight(500)
		);
	}
}
