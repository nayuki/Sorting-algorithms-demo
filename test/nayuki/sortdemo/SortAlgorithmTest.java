package nayuki.sortdemo;

import org.junit.Before;
import org.junit.Test;


public abstract class SortAlgorithmTest {
	
	public abstract SortAlgorithm getInstance();
	
	
	protected SortAlgorithm algor;
	
	
	@Before
	public void setUp() {
		algor = getInstance();
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
		algor.sort(arr);
		arr.assertSorted();
	}
	
	
	@Test
	public void testForward100() {
		TestSortArray arr = new TestSortArray(100);
		algor.sort(arr);
		arr.assertSorted();
	}
	
	
	@Test
	public void testReverse100() {
		TestSortArray arr = new TestSortArray(100);
		arr.reverse();
		algor.sort(arr);
		arr.assertSorted();
	}
	
}
