package com.test.wicket.ext;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DataBean {

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("M/d h:m");

	private String company;
	private Double price;
	private Double change;
	private Double  pctChange;
	private Date lastUpdate;
	private String industry;

	public DataBean( String company, double price, double change, double pctChange, String lastUpdate, String industry ) {
		this.company = company;
		this.price = price;
		this.change = change;
		this.pctChange = pctChange;
		try {
			this.lastUpdate = DATE_FORMAT.parse(lastUpdate);
		}
		catch (ParseException e) {
			throw new IllegalArgumentException(String.format("Invalid date value : %s", lastUpdate));
		}
		this.industry = industry;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChange() {
		return change;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public Double getPctChange() {
		return pctChange;
	}

	public void setPctChange(Double pctChange) {
		this.pctChange = pctChange;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

}


class DataHelper {

	static List<DataBean> dataset = new ArrayList<DataBean>();

	static {

	    dataset.add( new DataBean("3m Co",71.72,0.02,0.03,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("Alcoa Inc",29.01,0.42,1.47,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("Altria Group Inc",83.81,0.28,0.34,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("American Express Company",52.55,0.01,0.02,"9/1 12:00am", "Finance"));
	    dataset.add( new DataBean("American International Group, Inc.",64.13,0.31,0.49,"9/1 12:00am", "Services"));
	    dataset.add( new DataBean("AT&T Inc.",31.61,-0.48,-1.54,"9/1 12:00am", "Services"));
	    dataset.add( new DataBean("Boeing Co.",75.43,0.53,0.71,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("Caterpillar Inc.",67.27,0.92,1.39,"9/1 12:00am", "Services"));
	    dataset.add( new DataBean("Citigroup, Inc.",49.37,0.02,0.04,"9/1 12:00am", "Finance"));
	    dataset.add( new DataBean("E.I. du Pont de Nemours and Company",40.48,0.51,1.28,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("Exxon Mobil Corp",68.1,-0.43,-0.64,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("General Electric Company",34.14,-0.08,-0.23,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("General Motors Corporation",30.27,1.09,3.74,"9/1 12:00am", "Automotive"));
	    dataset.add( new DataBean("Hewlett-Packard Co.",36.53,-0.03,-0.08,"9/1 12:00am", "Computer"));
	    dataset.add( new DataBean("Honeywell Intl Inc",38.77,0.05,0.13,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("Intel Corporation",19.88,0.31,1.58,"9/1 12:00am", "Computer"));
	    dataset.add( new DataBean("International Business Machines",81.41,0.44,0.54,"9/1 12:00am", "Computer"));
	    dataset.add( new DataBean("Johnson & Johnson",64.72,0.06,0.09,"9/1 12:00am", "Medical"));
	    dataset.add( new DataBean("JP Morgan & Chase & Co",45.73,0.07,0.15,"9/1 12:00am", "Finance"));
	    dataset.add( new DataBean("McDonald\"s Corporation",36.76,0.86,2.40,"9/1 12:00am", "Food"));
	    dataset.add( new DataBean("Merck & Co., Inc.",40.96,0.41,1.01,"9/1 12:00am", "Medical"));
	    dataset.add( new DataBean("Microsoft Corporation",25.84,0.14,0.54,"9/1 12:00am", "Computer"));
	    dataset.add( new DataBean("Pfizer Inc",27.96,0.4,1.45,"9/1 12:00am", "Medical"));
	    dataset.add( new DataBean("The Coca-Cola Company",45.07,0.26,0.58,"9/1 12:00am", "Food"));
	    dataset.add( new DataBean("The Home Depot, Inc.",34.64,0.35,1.02,"9/1 12:00am", "Retail"));
	    dataset.add( new DataBean("The Procter & Gamble Company",61.91,0.01,0.02,"9/1 12:00am", "Manufacturing"));
	    dataset.add( new DataBean("United Technologies Corporation",63.26,0.55,0.88,"9/1 12:00am", "Computer"));
	    dataset.add( new DataBean("Verizon Communications",35.57,0.39,1.11,"9/1 12:00am", "Services"));
	    dataset.add( new DataBean("Wal-Mart Stores, Inc.",45.45,0.73,1.63,"9/1 12:00am", "Retail"));
	    dataset.add( new DataBean("Walt Disney Company (The) (Holding Company)",29.89,0.24,0.81,"9/1 12:00am", "Services"));

	}

	public static List<DataBean> list() {
		return dataset;
	}

	public static List<DataBean> list( int start, int limit, final String sort, final String dir ) {
		List<DataBean> result;

		if( sort != null ) {
			result = sort(dataset,sort,dir);
		}
		else {
			result = dataset;
		}

		return page(result,start,limit);
	}

	public static <T> List<T> page( final List<T> list, final int start, final int limit ) {
		/*
		 * extract the specified portion
		 */
		List<T> result = new ArrayList<T>();
		for( int i=0; i<limit && i+start<list.size(); i++ ) {
			result.add( list.get(start+i) );
		}

		return result;
	}

	public static <T> List<T> sort( final List<T> list, final String sort, final String dir ) {
		List<T> result = new ArrayList<T>(list);

		Collections.sort(result, new Comparator<T>() {


			public int compare(T o1, T o2) {
				Comparable v1 = value(o1,sort);
				Comparable v2 = value(o2,sort);
				if( !"DESC".equals(dir) ) {
					return v1.compareTo(v2);
				}
				else {
					return v2.compareTo(v1);
				}

		}} );

		return result;
	}

	public static <T> List<T> filter( final List<T> list, String column, String filter ) {
		List<T> result = new ArrayList<T>();
		for( T item : list ){
			String val = value(item, column);
			if( val != null && filter != null && val.toLowerCase().startsWith(filter.toLowerCase()) ) {
				result.add(item);
			}
		}

		return result;
	}

	public static <T> T value( Object obj, String fieldName ) {
		Field field;
		try {
			field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			T v1 = (T) field.get(obj);
			return v1;
		}
		catch (Exception e) {
			throw new IllegalArgumentException(String.format("Invalid field: '%s'", fieldName),e);
		}
	}

}
