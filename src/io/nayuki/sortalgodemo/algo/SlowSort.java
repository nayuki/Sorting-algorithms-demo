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
 * A pessimal "multiply and surrender" algorithm.
 * <ul>
 *   <li>Time complexity, all cases: &#x3a9;(<var>n</var><sup>log <var>n</var></sup>).</li>
 *   <li>Number of comparisons, all cases: &#x3a9;(<var>n</var><sup>log <var>n</var></sup>).</li>
 *   <li>Number of swaps, best case: &#x398;(1).</li>
 *   <li>Number of swaps, average case: &#x398;(<var>n</var><sup>2</sup>).</li>
 *   <li>Number of swaps, worst case: &#x398;(<var>n</var><sup>2</sup>).</li>
 *   <li>Auxiliary space complexity, all cases: &#x398;(<var>n</var>).</li>
 * </ul>
 */
public final class SlowSort implements SortAlgorithm {
	
	// Singleton
	public static final SortAlgorithm INSTANCE = new SlowSort();
	
	
	private SlowSort() {}
	
	
	@Override public String getName() {
		return "Slow sort";
	}
	
	
	/*---- Algorithm ----*/
	
	@Override public void sort(SortArray array) {
		array.setRange(0, array.length(), SortArray.ElementState.INACTIVE);
		sort(array, 0, array.length(), true);
	}
	
	
	private static void sort(SortArray array, int start, int end, boolean isMain) {
		if (!(0 <= start && start <= end && end <= array.length()))
			throw new IndexOutOfBoundsException();
		
		int length = end - start;
		if (length >= 2) {
			int mid = start + length / 2;
			sort(array, start, mid, false);
			sort(array, mid, end, false);
			array.setRange(start, end, SortArray.ElementState.ACTIVE);
			array.compareAndSwap(mid - 1, end - 1);
			array.setRange(start, end, SortArray.ElementState.INACTIVE);
		}
		if (isMain)
			array.setElement(end - 1, SortArray.ElementState.DONE);
		if (length >= 2)
			sort(array, start, end - 1, isMain);
	}
	
}
