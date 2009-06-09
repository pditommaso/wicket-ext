package com.test.wicket.ext;

import java.util.Iterator;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.ExtTemplate;
import org.wicketstuff.extjs.data.ObjectMap;
import org.wicketstuff.extjs.grid.ColumnMap;
import org.wicketstuff.extjs.ux.grid.ExtExpandingGridView;

public class GridExpandingPanelPage extends WebPage {

	public GridExpandingPanelPage() {

		ExtExpandingGridView<DataBean> grid;
		add( grid = new ExtExpandingGridView<DataBean> ("grid") {

			@Override
			public ObjectMap mapObject(DataBean obj, Integer index) {
				ObjectMap map = new ObjectMap("company", "price", "change", "lastUpdate", "industry", "pctChange","summary");
				if(obj != null ) {
					map.put("company", obj.getCompany());
					map.put("price", obj.getPrice());
					map.put("change", obj.getChange());
					map.put("lastUpdate", obj.getLastUpdate());
					map.put("industry", obj.getIndustry());
					map.put("pctChange", obj.getPctChange());
					map.put("summary", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec sollicitudin libero id elit. Sed euismod nonummy neque. Nam dignissim urna vel urna. Nunc aliquet. Nunc vel dui mattis magna varius condimentum. Etiam lacus enim, aliquam ultricies, luctus.");
				}
				return map;
			}

			@Override
			protected ColumnMap[] getColumns() {
				ColumnMap col1 = new ColumnMap("company", "Company name") .setSortable(true);
				ColumnMap col2 = new ColumnMap("price", "Price") .setSortable(true);
				ColumnMap col3 = new ColumnMap("change", "Change");
				ColumnMap col4 = new ColumnMap("lastUpdate", "Last update");

				return new ColumnMap[] { col1, col2, col3, col4 };
			}

			public Integer totalRecords() { return DataHelper.list().size(); }

			public Iterator<DataBean> iterator(Query query) {
				Integer start = query.getStart() != null ? query.getStart() : 0;
				Integer count = query.getLimit() != null ? query.getLimit() : 100;
				return DataHelper.list(start, count, query.getSort(), query.getDirection()).iterator();

			}} );


		grid.setWidth(600);
		grid.setHeight(500);
		grid.setPageSize(10);
		grid.setSortField("company");
	    grid.setTitle("Sample GridPanel");
	    grid.add( new ExtTemplate("row") );
	}
}
