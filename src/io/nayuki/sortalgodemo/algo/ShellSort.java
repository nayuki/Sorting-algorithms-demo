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
 * Sorts by running a sequence of insertion sorts with varying gaps.
 * <ul>
 *   <li>Time complexity, average case: approximately &#x398;(<var>n</var><sup>1.3</sup>).</li>
 *   <li>Number of comparisons, average case: approximately &#x398;(<var>n</var><sup>1.3</sup>).</li>
 *   <li>Number of swaps, average case: approximately &#x398;(<var>n</var><sup>1.3</sup>).</li>
 *   <li>Auxiliary space complexity, all cases: &#x398;(1).</li>
 * </ul>
 */
public final class ShellSort implements SortAlgorithm {
	
	// Singleton
	public static final SortAlgorithm INSTANCE = new ShellSort(new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1});
	
	
	private final int[] gapSequence;
	
	
	private ShellSort(int[] gaps) {
		gapSequence = gaps;
	}
	
	
	@Override public String getName() {
		return "Shell sort";
	}
	
	
	/*---- Algorithm ----*/
	
	@Override public void sort(SortArray array) {
		int length = array.length();
		for (int step : gapSequence) {
			array.setRange(0, length, SortArray.ElementState.INACTIVE);
			
			// Do an insertion sort with this step size
			for (int i = 0; i < length; i++) {
				for (int j = i; j >= step && array.compareAndSwap(j - step, j); j -= step);
			}
		}
	}
	
}
