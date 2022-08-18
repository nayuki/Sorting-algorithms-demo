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

import java.util.Objects;
import org.junit.Test;


/**
 * A suite of test cases that are appropriate for all sorting algorithms.
 * Each sort algo needs to extend this common base class and implement {@link #getInstance()}.
 */
public abstract class SortAlgorithmTest {
	
	// Returns an instance of the sorting algorithm to be tested.
	public abstract SortAlgorithm getInstance();
	
	
	/*---- Test cases ----*/
	
	@Test public void testRandom10() {
		testSorting(10, ArrayOrder.RANDOM);
	}
	
	@Test public void testRandom30() {
		testSorting(30, ArrayOrder.RANDOM);
	}
	
	@Test public void testRandom100() {
		testSorting(100, ArrayOrder.RANDOM);
	}
	
	
	@Test public void testForward100() {
		testSorting(100, ArrayOrder.FORWARD);
	}
	
	@Test public void testReverse100() {
		testSorting(100, ArrayOrder.REVERSE);
	}
	
	
	@Test public void testRandomSizes() {
		final int TRIALS = 100;
		final int SIZE_LIMIT = 100;
		for (int i = 0; i < TRIALS; i++) {
			int size = AbstractSortArray.random.nextInt(SIZE_LIMIT) + 1;
			testSorting(size, ArrayOrder.RANDOM);
		}
	}
	
	
	
	/*---- Utilities ----*/
	
	protected void testSorting(int size, ArrayOrder type) {
		// Check arguments
		if (size < 0)
			throw new IllegalArgumentException();
		Objects.requireNonNull(type);
		
		// Create array
		var arr = new TestSortArray(size);
		switch (type) {
			case FORWARD:  break;
			case REVERSE:  arr.reverse();  break;
			case RANDOM :  arr.shuffle();  break;
			default:  throw new AssertionError();
		}
		
		// Sort and check
		getInstance().sort(arr);
		arr.assertSorted();
	}
	
	
	protected enum ArrayOrder {
		FORWARD, REVERSE, RANDOM;
	}
	
}
