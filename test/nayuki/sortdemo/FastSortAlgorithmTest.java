package nayuki.sortdemo;

import org.junit.Test;


// Contains test cases with larger arrays, appropriate for O(n log n) sort algorithms.
public abstract class FastSortAlgorithmTest extends SortAlgorithmTest {
	
	@Test public void testRandom1e3() {
		testRandom(1 * 1000);
	}
	
	@Test public void testRandom1e4() {
		testRandom(10 * 1000);
	}
	
	@Test public void testRandom1e5() {
		testRandom(100 * 1000);
	}
	
}
