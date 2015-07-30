package nayuki.sortdemo;

import java.util.Random;


/**
 * An array that can be sorted and have the sorting process visualized.
 */
public abstract class SortArray {
	
	protected int[] values;
	
	
	
	public SortArray(int size) {
		// Initialize in order: [0, 1, 2, ..., size-1]
		values = new int[size];
		for (int i = 0; i < values.length; i++)
			values[i] = i;
	}
	
	
	
	/* Field getters */
	
	public int length() {
		return values.length;
	}
	
	
	/* Comparison and swapping */
	
	// Compares the values at the two given array indices.
	// Returns a negative number if array[i] < array[j], zero if array[i] == array[j], or a positive number if array[i] > array[j].
	// Do not assume that this returns only -1, 0, or 1.
	public int compare(int i, int j) {
		if (values[i] < values[j])
			return -1;
		else if (values[i] > values[j])
			return 1;
		else
			return 0;
	}
	
	
	// Swaps the values at the two given array indices.
	public void swap(int i, int j) {
		int temp = values[i];
		values[i] = values[j];
		values[j] = temp;
	}
	
	
	// Compares the values at the two given array indices, swaps if and only if array[i] > array[j], and returns whether a swap occurred.
	public boolean compareAndSwap(int i, int j) {
		if (compare(j, i) < 0) {
			swap(i, j);
			return true;
		} else
			return false;
	}
	
	
	public void shuffle() {
		// Fisher-Yates / Knuth shuffle
		for (int i = length() - 1; i >= 0; i--)
			swap(i, random.nextInt(i + 1));
	}
	
	
	/* Sorting progress visualization */
	
	public abstract void setActive(int index);
	public abstract void setInactive(int index);
	public abstract void setDone(int index);
	
	public abstract void setActive(int start, int end);
	public abstract void setInactive(int start, int end);
	public abstract void setDone(int start, int end);
	
	
	public static final Random random = new Random();
	
}
