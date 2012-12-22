package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.SelectionSort;


public final class SelectionSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new SelectionSort();
	}
	
}
