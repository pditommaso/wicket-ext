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

package org.wicketstuff.extjs;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Wrapper class to manage easily JavaScript configuration objects to be provided to Ext classes
 *
 * @author Paolo Di Tommaso
 *
 */
public class Config  implements Serializable, Map<String,Object> {

	Map<String,Object> map = new HashMap<String, Object>();


	/**
	 * Default constructor
	 */
	public Config() {

	}

	public Config( Config copy ) {
		map = new HashMap<String,Object>(copy.map);
	}

	public Config( String key, Object value ) {
		set(key,value);
	}

	@SuppressWarnings("unchecked")
	public <T> T get( String name ) {
		return (T)map.get(name);
	}


	public Config set(String name, Object value ) {
		map.put(name, value);
		return this;
	}

	public Config put(String name, Object value ) {
		map.put(name, value);
		return this;
	}


	public Config putAll( Config config ) {
		map.putAll(config.map);
		return this;
	}

	/**
	 * Put all entris in specified object that does NOT exist in the current instance
	 */
	public Config putIfNotExists( Config config ) {
		for( Map.Entry<String, Object> e : config.map.entrySet() ) {
			if( !map.containsKey(e.getKey()) ) {
				map.put(e.getKey(), e.getValue());
			}
		}
		return this;
	}

	public Config putIfNotExists( String key, Object value ) {
		if( !map.containsKey(key) ) {
			map.put(key, value);
		}
		return this;
	}

	public Set<Map.Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	@Override
	public String toString() {
		return Ext.serialize(map).toString();
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public Object get(Object key) {
		return map.get(key);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		map.putAll(m);
	}

	public Object remove(Object key) {
		return map.remove(key);
	}

	public int size() {
		return map.size();
	}

	public Collection<Object> values() {
		return map.values();
	}

}
