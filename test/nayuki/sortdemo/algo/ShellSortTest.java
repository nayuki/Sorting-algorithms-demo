package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortAlgorithmTest;
import nayuki.sortdemo.algo.ShellSort;


public final class ShellSortTest extends SortAlgorithmTest {
	
	public SortAlgorithm getInstance() {
		return new ShellSort();
	}
	
}
