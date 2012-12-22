package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.InsertionSort;


public final class InsertionSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new InsertionSort();
	}
	
}
