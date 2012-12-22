package nayuki.sortdemo.algo;

import nayuki.sortdemo.FastSortAlgorithmTest;
import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.algo.QuickSortDoubleEnded;


public final class QuickSortDoubleEndedTest extends FastSortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new QuickSortDoubleEnded();
	}
	
}
