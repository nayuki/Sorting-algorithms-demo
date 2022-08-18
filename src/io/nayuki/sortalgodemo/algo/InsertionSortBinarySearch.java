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

import io.nayuki.sortalgodemo.core.SortAlgorithm;
import io.nayuki.sortalgodemo.core.SortArray;


/**
 * Sorts by swapping the next unsorted item into the correct position in the sorted subarray.
 * The correct position is determined using binary search instead of linear search.
 * The time complexity is in <var>O</var>(<var>n</var><sup>2</sup>).
 */
public final class InsertionSortBinarySearch implements SortAlgorithm {
	
	// Singleton
	public static final SortAlgorithm INSTANCE = new InsertionSortBinarySearch();
	
	
	private InsertionSortBinarySearch() {}
	
	
	@Override public String getName() {
		return "Insertion sort (binary search)";
	}
	
	
	/*---- Algorithm ----*/
	
	@Override public void sort(SortArray array) {
		int length = array.length();
		array.setRange(0, length, SortArray.ElementState.INACTIVE);
		for (int i = 0; i < length; i++) {
			int index = binarySearch(array, i, 0, i);
			for (int j = i; j - 1 >= index; j--)
				array.swap(j, j - 1);
		}
		array.setRange(0, length, SortArray.ElementState.DONE);
	}
	
	
	private static int binarySearch(SortArray array, int index, int start, int end) {
		while (start != end) {
			int mid = (start + end) / 2;
			int temp = array.compare(index, mid);
			if (temp < 0)
				end = mid;
			else if (temp > 0)
				start = mid + 1;
			else
				return mid;
		}
		return start;
	}
	
}
