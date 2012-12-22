package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.StupidSort;


public final class StupidSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new StupidSort();
	}
	
}
