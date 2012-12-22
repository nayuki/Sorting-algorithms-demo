package nayuki.sortdemo.algo;

import nayuki.sortdemo.FastSortAlgorithmTest;
import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.algo.QuickSortSliding;


public final class QuickSortSlidingTest extends FastSortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new QuickSortSliding();
	}
	
}
