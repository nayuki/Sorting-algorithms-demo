package nayuki.sortdemo;

import org.junit.Assert;


// An array for test purposes, not supporting graphics.
final class TestSortArray extends AbstractSortArray {
	
	/* Initialization */
	
	public TestSortArray(int size) {
		super(size);
	}
	
	public void reverse() {
		for (int i = 0; i < values.length / 2; i++)
			swap(i, values.length - 1 - i);
	}
	
	public void shuffle() {
		Utils.shuffle(values);
	}
	
	
	/* Test */
	
	public void assertSorted() {
		for (int i = 0; i < values.length - 1; i++)
			Assert.assertTrue(values[i] <= values[i + 1]);
	}
	
	
	/* Ignore graphics visualization calls */
	
	public void setActive(int index) {}
	public void setInactive(int index) {}
	public void setDone(int index) {}
	
	public void setActive(int start, int end) {}
	public void setInactive(int start, int end) {}
	public void setDone(int start, int end) {}
	
}
