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

package io.nayuki.sortalgodemo.simple;

import io.nayuki.sortalgodemo.core.AbstractSortArray;


final class SimpleSortArray extends AbstractSortArray implements Cloneable {
	
	/*---- Fields ---*/
	
	public long comparisonCount;
	public long swapCount;
	
	
	
	/*---- Constructors ----*/
	
	public SimpleSortArray(int size) {
		super(size);
		shuffle();
		comparisonCount = 0;
		swapCount = 0;
	}
	
	
	
	/*---- Methods ----*/
	
	public int compare(int i, int j) {
		comparisonCount++;
		return super.compare(i, j);
	}
	
	
	public void swap(int i, int j) {
		swapCount++;
		super.swap(i, j);
	}
	
	
	public SimpleSortArray clone() {
		try {
			SimpleSortArray result = (SimpleSortArray)super.clone();
			result.values = result.values.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(e);
		}
	}
	
}
