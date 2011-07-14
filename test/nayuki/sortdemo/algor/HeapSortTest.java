package nayuki.sortdemo.algor;

import nayuki.sortdemo.FastSortAlgorithmTest;
import nayuki.sortdemo.SortAlgorithm;


public final class HeapSortTest extends FastSortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new HeapSort();
	}
	
}
