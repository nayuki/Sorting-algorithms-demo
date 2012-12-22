package nayuki.sortdemo;


public abstract class AbstractSortArray implements SortArray {
	
	protected int[] values;
	
	
	
	public AbstractSortArray(int size) {
		// Initialize in order: [0, 1, 2, ..., size-1]
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i;
	}
	
	
	
	public int length() {
		return values.length;
	}
	
	
	public int compare(int i, int j) {
		if (values[i] < values[j])
			return -1;
		else if (values[i] > values[j])
			return 1;
		else
			return 0;
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
	
}
