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

package org.wicketstuff.extjs.util;

import java.io.Serializable;
import java.util.Map;

import org.apache.wicket.Response;
import org.apache.wicket.util.string.Strings;
import org.wicketstuff.extjs.data.IDataSource;
import org.wicketstuff.extjs.data.ObjectMapper;

public class XmlRenderer<T> implements Serializable {

	public static final String ITEM = "item";
	public static final String TOTAL_SIZE = "totalSize";

	private ObjectMapper<T> mapper;

	private int count;

	public XmlRenderer( ObjectMapper<T> mapper ) {
		this.mapper = mapper;
	}


	public void render( T object, Response response ) {
		StringBuilder result = new StringBuilder();
		result.append("<").append(ITEM).append(">");
		Map<String,Object> map = mapper.mapObject(object, count++);
		for( Map.Entry<String, Object> entry : map.entrySet() ) {
			CharSequence key = escape(entry.getKey());
			CharSequence value = escape(entry.getValue());
			result
				.append("<") .append(key) .append(">")
				.append( value )
				.append("</") .append(key) .append(">" );

		}
		result.append("</").append(ITEM).append(">");
		response.write( result );
	}


	public void renderHeader(Response response) {
		response.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		response.write("<dataset>");
		Integer size = totalRecords();
		if( size != null ) {
			response.write(String.format("<%s>%s</%s>", TOTAL_SIZE, size, TOTAL_SIZE));
		}
		count = 0;
	}

	public void renderFooter(Response response) {
		response.write("</dataset>");
	}

	private CharSequence escape( Object value ) {
		//TODO use a better escape function
		return Strings.escapeMarkup(value != null ? value.toString() : "");
	}

	protected Integer totalRecords() {
		if( mapper instanceof IDataSource ) {
			return ((IDataSource<T>) mapper).totalRecords();
		}
		return null;
	}
}

