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

/* 
 * Sort demo main class
 * 
 * Color legend:
 * - Blue: Normal
 * - Green: In final position
 * - Yellow: Comparing
 * - Gray: Inactive
 */

package io.nayuki.sortalgodemo.visual;

import java.util.Arrays;
import io.nayuki.sortalgodemo.algo.*;
import io.nayuki.sortalgodemo.core.SortAlgorithm;


/**
 * The main class for the sort demo desktop GUI application.
 */
public final class SortDemo {
	
	// Run with no command line arguments.
	public static void main(String[] args) {
		// Set up list of algorithms and go
		SortAlgorithm[] algos = {
			BubbleSort.INSTANCE,
			CocktailSort.INSTANCE,
			SelectionSort.INSTANCE,
			PancakeSort.INSTANCE,
			QuasiPancakeSort.INSTANCE,
			GnomeSort.INSTANCE,
			InsertionSort.INSTANCE,
			InsertionSortBinarySearch.INSTANCE,
			RotationMergeSort.INSTANCE,
			ShellSort.INSTANCE,
			HeapSort.INSTANCE,
			QuickSortDoubleEnded.INSTANCE,
			QuickSortSliding.INSTANCE,
			SlowSort.INSTANCE,
			StoogeSort.INSTANCE,
			StupidSort.INSTANCE,
			BozoSort.INSTANCE,
		};
		new LaunchFrame(Arrays.asList(algos));
	}
	
}
