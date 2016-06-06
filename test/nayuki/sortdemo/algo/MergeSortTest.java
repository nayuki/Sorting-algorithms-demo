package nayuki.sortdemo.algo;
import nayuki.sortdemo.FastSortAlgorithmTest;
import nayuki.sortdemo.SortAlgorithm;

public class MergeSortTest extends FastSortAlgorithmTest {

	@Override
	public SortAlgorithm getInstance() {
		return new MergeSort();
	}
}