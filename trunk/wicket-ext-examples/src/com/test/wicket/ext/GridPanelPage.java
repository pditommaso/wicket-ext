package com.test.wicket.ext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.extjs.grid.ColumnMap;
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
	}
	
	
	public GridPanelPage() { 
		
		add( new ExtGridPanel<SimpleBean> ("grid") {


			@Override
			protected ColumnMap[] getColumns() {
				ColumnMap col1 = new ColumnMap("alpha", "Colonna 1");
				ColumnMap col2 = new ColumnMap("beta", "Colonna 2");
				ColumnMap col3 = new ColumnMap("gamma", "Colonna 3");

				return new ColumnMap[] { col1, col2, col3 };
			}

			public Long totalRecords() { return 300L; }
			
			@Override
			public Iterator<SimpleBean> iterator(String filter, String direction, Integer start, Integer count) {
				List<SimpleBean> result = new ArrayList<SimpleBean>();
				if( start == null ) { start = 0; }
				if( count == null ) { count = 100; }
				for( int i=0; i<count; i++ ) { 
					result.add(new SimpleBean(String.valueOf(start+i), String.valueOf(Math.random()), String.valueOf( Math.round(Math.random()*10) ) ));
				} 
				return result.iterator();

			}}
			.setPageSize(50)
		);
	}
}
