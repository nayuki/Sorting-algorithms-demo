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


/**
 * A stateless function object that sorts an array. The function also optionally the array
 * element states (e.g. active/inactive/done) for visualization during the process of sorting.
 */
public interface SortAlgorithm {
	
	/**
	 * Sorts the specified array in ascending order using this algorithm.
	 * @param array the array to sort, which is not {@code null}
	 * @throws NullPointerException if {@code array} is {@code null}
	 */
	public void sort(SortArray array);
	
	
	/**
	 * Returns the name of this sorting algorithm; for example, "Bubble sort".
	 * @return the name of this sorting algorithm, which is not {@code null}
	 */
	public String getName();
	
}
