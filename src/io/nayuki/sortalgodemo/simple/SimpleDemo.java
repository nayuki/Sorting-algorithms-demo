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

import io.nayuki.sortalgodemo.algo.*;
import io.nayuki.sortalgodemo.core.SortAlgorithm;


public final class SimpleDemo {
	
	/*---- Default configuration ----*/
	
	private static final SortAlgorithm[] ALGORITHMS = {
		HeapSort.INSTANCE,
		QuickSortDoubleEnded.INSTANCE,
		QuickSortSliding.INSTANCE,
		ShellSort.INSTANCE,
		RotationMergeSort.INSTANCE,
		BubbleSort.INSTANCE,
		CocktailSort.INSTANCE,
		SelectionSort.INSTANCE,
		GnomeSort.INSTANCE,
		InsertionSort.INSTANCE,
		InsertionSortBinarySearch.INSTANCE,
		PancakeSort.INSTANCE,
		QuasiPancakeSort.INSTANCE,
		StoogeSort.INSTANCE,
		StupidSort.INSTANCE,
		BozoSort.INSTANCE,
		SlowSort.INSTANCE,
	};
	
	private static final int DEFAULT_SIZE = 300;
	
	
	
	/*---- Main program ----*/
	
	public static void main(String[] args) {
		// Handle command line arguments
		int size;
		if (args.length == 0)
			size = DEFAULT_SIZE;
		else if (args.length == 1)
			size = Integer.parseInt(args[0]);
		else {
			System.err.println("Usage: java nayuki.sortalgodemo.algo.simple.SimpleDemo [ArraySize]");
			System.exit(1);
			return;
		}
		if (size <= 0)
			throw new IllegalArgumentException("Array size must be positive");
		
		// Sort with each algorithm, and print statistics
		System.err.println("Algorithm name\tNumber of comparisons\tNumber of swaps");
		SimpleSortArray referenceArray = new SimpleSortArray(size);
		for (SortAlgorithm algo : ALGORITHMS) {
			SimpleSortArray array = referenceArray.clone();
			algo.sort(array);
			System.err.printf("%s\t%d\t%d%n", algo.getName(), array.comparisonCount, array.swapCount);
		}
	}
	
}
