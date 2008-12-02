package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.grid.ColumnModel;
import org.wicketstuff.extjs.grid.ExtGridPanel;

public class GridPanelPage extends WebPage {

	static class SimpleBean { 
		
		private String alpha;
		private String beta;
		private String gamma;
		
		public SimpleBean( String a1, String a2, String a3 ) { 
			alpha = a1;
			beta = a2;
			gamma = a3;
		}
	}
	
	
	public GridPanelPage() { 
		
		add( new ExtGridPanel<SimpleBean> ("grid") {

			@Override
			protected Map<String, Object> mapObject(SimpleBean object, int index) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("col1",null);
				map.put("col2",null);
				map.put("col3",null);
				if( object != null ) { 
					map.put("col1",object.alpha);
					map.put("col2",object.beta);
					map.put("col3",object.gamma);
				}
				return map;
			}

			@Override
			protected Iterator<SimpleBean> onLoad() {
				List<SimpleBean> result = new ArrayList<SimpleBean>();
				result.add(new SimpleBean("One", "Two", "Three"));
				result.add(new SimpleBean("Four", "Five", "Six"));
				result.add(new SimpleBean("Seven", "Eight", "Nive"));
				return result.iterator();
			}

			@Override
			protected ColumnModel[] getColumns() {
				ColumnModel col1 = new ColumnModel("col1", "Colonna 1");
				ColumnModel col2 = new ColumnModel("col2", "Colonna 2");
				ColumnModel col3 = new ColumnModel("col3", "Colonna 3");

				return new ColumnModel[] { col1, col2, col3 };
			}} );
	}
}
