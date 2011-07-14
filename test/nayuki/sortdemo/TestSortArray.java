package nayuki.sortdemo;

import static org.junit.Assert.fail;


final class TestSortArray implements SortArray {
	
	private int[] values;
	
	
	/* Initialization */
	
	public TestSortArray(int size) {
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i;
	}
	
	public void reverse() {
		for (int i = 0; i < values.length / 2; i++) {
			int j = values.length - 1 - i;
			int temp = values[i];
			values[i] = values[j];
			values[j] = temp;
		}
	}
	
	public void shuffle() {
		Utils.shuffle(values);
	}
	
	
	/* Sorting */
	
	public int length() {
		return values.length;
	}
	
	public int compare(int i, int j) {
		return Utils.compare(values[i], values[j]);
	}
	
	public void swap(int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
	
	public boolean compareAndSwap(int i, int j) {
		if (compare(j, i) < 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	/* Test */
	
	public void assertSorted() {
		for (int i = 0; i < values.length - 1; i++) {
			if (values[i] > values[i + 1])
				fail("Not sorted");
		}
	}
	
	
	/* Ignore graphics visualization calls */
	
	public void setActive(int index) {}
	public void setInactive(int index) {}
	public void setDone(int index) {}
	
	public void setActive(int start, int end) {}
	public void setInactive(int start, int end) {}
	public void setDone(int start, int end) {}
	
}
