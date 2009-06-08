/*
 *  Copyright 2008 Wicket-Ext
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wicketstuff.extjs.data;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Defines a generic data-link interface to used as data provider by Ext components
 *
 * @author Paolo Di Tommaso
 */

public interface IDataSource<T> extends ObjectMapper<T> {

	Iterator<T> iterator( Query params );

	Integer totalRecords();

	/**
	 * Defines the parameters for the query to extract required data
	 *
	 * @author Paolo Di Tommaso
	 *
	 */
	static public class Query {
		private static final Logger log = LoggerFactory.getLogger(Query.class);

		private Integer start;
		private Integer limit;
		private String direction;
		private String sort;
		private String filter;

		public Integer getStart() {
			return start;
		}

		public Query setStart(Integer start) {
			this.start = start;
			return this;
		}

		public Query setStart(String start) {
			this.start = num(start,"Paramer 'start' is not a valid number: {}");
			return this;
		}

		public Integer getLimit() {
			return limit;
		}

		public Query setLimit(Integer limit) {
			this.limit = limit;
			return this;
		}

		public Query setLimit( String limit ) {
			this.limit = num(limit,"Parameter 'limit' is not a valid number: {}");
			return this;
		}

		public String getDirection() {
			return direction;
		}

		public Query setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public String getSort() {
			return sort;
		}

		public Query setSort(String sort) {
			this.sort = sort;
			return this;
		}

		public String getFilter() {
			return filter;
		}

		public Query setFilter(String filter) {
			this.filter = filter;
			return this;
		}

		private Integer num( String value, String message) {
			if( value == null ) {
				return null;
			}

			Integer result = null;
			try {
				result = Integer.parseInt(value);
			} catch( NumberFormatException e ) {
				log.warn(message,value);
			}
			return result;

		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder()
				.append("Query[filter='") .append(filter) .append("', ")
				.append("Query[sort='") .append(sort) .append("', ")
				.append("Query[dir='") .append(direction) .append("', ")
				.append("Query[start=") .append(start) .append(", ")
				.append("Query[limit=") .append(limit) .append("]");
			return result.toString();
		}
	}

}
