/* 
 * Sorting algorithms demo (Java)
 * 
 * Copyright (c) Project Nayuki
 * https://www.nayuki.io/page/sorting-algorithms-demo-java
 * 
 * (MIT License)
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * - The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 * - The Software is provided "as is", without warranty of any kind, express or
 *   implied, including but not limited to the warranties of merchantability,
 *   fitness for a particular purpose and noninfringement. In no event shall the
 *   authors or copyright holders be liable for any claim, damages or other
 *   liability, whether in an action of contract, tort or otherwise, arising from,
 *   out of or in connection with the Software or the use or other dealings in the
 *   Software.
 */

package io.nayuki.sortalgodemo.core;

import java.util.stream.IntStream;


/**
 * A convenience class that manages an array and implements most of the methods.
 */
public abstract class AbstractSortArray implements SortArray {
	
	/*---- Fields ----*/
	
	protected int[] values;
	
	
	
	/*---- Constructors ----*/
	
	public AbstractSortArray(int size) {
		if (size < 0)
			throw new IllegalArgumentException("Negative array size");
		// Initialize in order: [0, 1, 2, ..., size-1]
		values = IntStream.range(0, size).toArray();
	}
	
	
	
	/*---- Methods ----*/
	
	/* Field getters */
	
	public int length() {
		return values.length;
	}
	
	
	/* Comparison and swapping */
	
	public int compare(int i, int j) {
		if (!(0 <= i && i < length() && 0 <= j && j < length()))
			throw new IndexOutOfBoundsException();
		return Integer.compare(values[i], values[j]);
	}
	
	
	public void swap(int i, int j) {
		if (!(0 <= i && i < length() && 0 <= j && j < length()))
			throw new IndexOutOfBoundsException();
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
	
	
	public boolean compareAndSwap(int i, int j) {
		if (compare(i, j) > 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	/* Sorting progress visualization */
	
	// Does nothing by default; can be overridden.
	public void setElement(int index, SortArray.ElementState state) {}
	
	// Does nothing by default; can be overridden.
	public void setRange(int start, int end, SortArray.ElementState state) {}
	
}
