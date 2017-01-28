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

package io.nayuki.sortalgodemo.visual;

import io.nayuki.sortalgodemo.core.SortAlgorithm;


final class SortThread extends Thread {
	
	/*---- Static constants ----*/
	
	private static final int STARTING_DELAY = 1000;  // In milliseconds
	
	
	/*---- Fields ----*/
	
	private SortAlgorithm algorithm;
	private VisualSortArray array;
	
	
	/*---- Constructors ----*/
	
	public SortThread(VisualSortArray array, SortAlgorithm algo) {
		this.array = array;
		this.algorithm = algo;
		new SortFrame(algorithm.getName(), array.getCanvas(), this);
	}
	
	
	/*---- Methods ----*/
	
	public void run() {
		try {
			Thread.sleep(STARTING_DELAY);
			algorithm.sort(array);
			try {
				array.assertSorted();
				System.out.printf("%s: %d comparisons, %d swaps%n",
					algorithm.getName(), array.getComparisonCount(), array.getSwapCount());
			} catch (AssertionError e) {
				System.out.printf("%s: Sorting failed%n", algorithm.getName());
			}
		}
		catch (StopException|InterruptedException e) {}
	}
	
}
