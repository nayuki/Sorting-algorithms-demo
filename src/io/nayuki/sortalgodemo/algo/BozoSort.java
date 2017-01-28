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

package io.nayuki.sortalgodemo.algo;

import io.nayuki.sortalgodemo.core.AbstractSortAlgorithm;
import io.nayuki.sortalgodemo.core.AbstractSortArray;
import io.nayuki.sortalgodemo.core.SortAlgorithm;
import io.nayuki.sortalgodemo.core.SortArray;


/**
 * Sorts by randomly selecting two elements and swapping them
 * if they are in inverted order, until the array is sorted.
 * The time complexity is in <var>O</var>(<var>n</var><sup>3</sup> log <var>n</var>).
 */
public final class BozoSort extends AbstractSortAlgorithm {
	
	// The singleton instance.
	public static final SortAlgorithm INSTANCE = new BozoSort();
	
	
	public void sort(SortArray array) {
		int length = array.length();
		while (!isSorted(array)) {
			int i = AbstractSortArray.random.nextInt(length);
			int j = AbstractSortArray.random.nextInt(length);
			array.compareAndSwap(Math.min(i, j), Math.max(i, j));
		}
		array.setRange(0, length, SortArray.ElementState.DONE);
	}
	
	
	// Tests whether the entire array is in non-decreasing order.
	private static boolean isSorted(SortArray array) {
		for (int i = 0; i < array.length() - 1; i++) {
			if (array.compare(i, i + 1) > 0)
				return false;
		}
		return true;
	}
	
	
	// Private constructor.
	private BozoSort() {
		super("Bozo sort");
	}
	
}
