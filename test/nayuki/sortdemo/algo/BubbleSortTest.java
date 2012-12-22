package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.BubbleSort;


public final class BubbleSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new BubbleSort();
	}
	
}
