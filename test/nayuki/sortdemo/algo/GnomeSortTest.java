package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.GnomeSort;


public final class GnomeSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new GnomeSort();
	}
	
}
