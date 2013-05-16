/*
 * Copyright 2013, The Thymeleaf Project (http://www.thymeleaf.org/)
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

package org.thymeleaf.extras.eclipse.dialect;

import org.thymeleaf.extras.eclipse.dialect.xml.AttributeProcessor;
import org.thymeleaf.extras.eclipse.dialect.xml.DialectItem;
import org.thymeleaf.extras.eclipse.dialect.xml.ElementProcessor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * Representation of a file containing dialect information.
 * 
 * @author Emanuel Rabina
 */
public class DialectFile {

	private final TreeSet<DialectItem> dialectitems = new TreeSet<DialectItem>(new Comparator<DialectItem>() {
		@Override
		public int compare(DialectItem item1, DialectItem item2) {
			return item1.getName().compareTo(item2.getName());
		}
	});

	/**
	 * Constructor, associate this class with a dialect's processed items.
	 * 
	 * @param dialectitems
	 */
	public DialectFile(List<DialectItem> dialectitems) {

		this.dialectitems.addAll(dialectitems);
	}

	/**
	 * Get all the attribute processors in this dialect.
	 * 
	 * @return Attribute processors.
	 */
	public List<AttributeProcessor> getAttributeProcessors() {

		return getDialectItemsByType(AttributeProcessor.class);
	}

	/**
	 * Get all the element processors in this dialect.
	 * 
	 * @return Element processors.
	 */
	public List<ElementProcessor> getElementProcessors() {

		return getDialectItemsByType(ElementProcessor.class);
	}

	/**
	 * Get all of the given type of dialect item in this dialect.
	 * 
	 * @param type Item type.
	 * @param <T>  Item type.
	 * @return List of all dialect items of the given type.
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> getDialectItemsByType(Class<T> type) {

		ArrayList<T> items = new ArrayList<T>();
		for (DialectItem dialectitem: dialectitems) {
			if (type.isAssignableFrom(dialectitem.getClass())) {
				items.add((T)dialectitem);
			}
		}
		return items;
	}
}
