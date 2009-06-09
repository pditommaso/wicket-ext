package com.test.wicket.ext;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.extjs.ExtTemplate;
import org.wicketstuff.extjs.data.ObjectMap;
import org.wicketstuff.extjs.grid.ExtDataView;
import org.wicketstuff.extjs.ux.grid.ExtSearchDataView;

public class DataViewPage extends WebPage {

	public DataViewPage() {

		/*
		 * the data view control
		 */
		ExtDataView<DataBean> dataView = new ExtSearchDataView<DataBean> ("data") {

			private Integer total;

			public Integer totalRecords() { return total; }

			public Iterator<DataBean> iterator(Query query) {
				Integer start = query.getStart() != null ? query.getStart() : 0;
				Integer count = query.getLimit() != null ? query.getLimit() : 100;

				List<DataBean> list = DataHelper.list();
				if( !Strings.isEmpty(query.getFilter()) ) {
					list = DataHelper.filter(list, "company", query.getFilter());
				}
				total = list.size(); // <-- update total number of records

				return DataHelper.page(list,start,count).iterator();
			}

			public ObjectMap mapObject(DataBean bean, Integer index) {
				ObjectMap obj = new ObjectMap("company","price","change","industry");
				if( bean!= null ) {
					obj.put("company", bean.getCompany());
					obj.put("price", bean.getPrice());
					obj.put("change", bean.getChange());
					obj.put("industry", bean.getIndustry());
				}
				return obj;
			}};

		add(dataView);
		dataView.add(new ExtTemplate("tpl")); // <-- the inner template
		dataView.setTitle("Sample Data View");
		dataView.setPageSize(10);
	}
}
