package nayuki.sortdemo;

import org.junit.Before;
import org.junit.Test;


public abstract class SortAlgorithmTest {
	
	public abstract SortAlgorithm getInstance();
	
	
	protected SortAlgorithm algo;
	
	
	@Before
	public void setUp() {
		algo = getInstance();
	}
	
	
	@Test
	public void testRandom10() {
		testRandom(10);
	}
	
	@Test
	public void testRandom30() {
		testRandom(30);
	}
	
	@Test
	public void testRandom100() {
		testRandom(100);
	}
	
	protected void testRandom(int size) {
		TestSortArray arr = new TestSortArray(size);
		arr.shuffle();
		algo.sort(arr);
		arr.assertSorted();
	}
	
	
	@Test
	public void testForward100() {
		TestSortArray arr = new TestSortArray(100);
		algo.sort(arr);
		arr.assertSorted();
	}
	
	
	@Test
	public void testReverse100() {
		TestSortArray arr = new TestSortArray(100);
		arr.reverse();
		algo.sort(arr);
		arr.assertSorted();
	}
	
}
