package com.test.wicket.ext;

import java.util.Iterator;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.grid.ColumnMap;
import org.wicketstuff.extjs.grid.ExtGridView;

public class GridPanelPage extends WebPage {

	public GridPanelPage() {

		add( new ExtGridView<DataBean> ("grid") {

			@Override
			protected ColumnMap[] getColumns() {
				ColumnMap col1 = new ColumnMap("company", "Company name") .setSortable(true);
				ColumnMap col2 = new ColumnMap("price", "Price") .setSortable(true);
				ColumnMap col3 = new ColumnMap("change", "Change");
				ColumnMap col4 = new ColumnMap("lastUpdate", "Last update");

				return new ColumnMap[] { col1, col2, col3, col4 };
			}

			public Integer totalRecords() { return DataHelper.list().size(); }

			@Override
			public Iterator<DataBean> iterator(Query query) {
				Integer start = query.getStart() != null ? query.getStart() : 0;
				Integer count = query.getLimit() != null ? query.getLimit() : 100;
				return DataHelper.list(start, count, query.getSort(), query.getDirection()).iterator();

			}}
			.setPageSize(10)
			.setSortField("company")
		    .setTitle("Sample GridPanel")
			.setWidth(600)
			.setHeight(500)

		);
	}
}
