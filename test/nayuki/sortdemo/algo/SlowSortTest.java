package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;


public final class SlowSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new SlowSort();
	}
	
}
