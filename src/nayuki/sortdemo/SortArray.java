package nayuki.sortdemo;


/**
 * An array that can be sorted and have the sorting process visualized.
 */
public interface SortArray {
	
	/* Field getters */
	
	public int length();
	
	
	/* Comparison and swapping */
	
	// Compares the values at the two given array indices.
	// Returns a negative number if array[i] < array[j], zero if array[i] == array[j], or a positive number if array[i] > array[j].
	// Do not assume that this returns only -1, 0, or 1.
	public int compare(int i, int j);
	
	// Swaps the values at the two given array indices.
	public void swap(int i, int j);
	
	// Compares the values at the two given array indices, swaps if and only if array[i] > array[j], and returns whether a swap occurred.
	public boolean compareAndSwap(int i, int j);
	
	
	/* Sorting progress visualization */
	
	public void setActive(int index);
	public void setInactive(int index);
	public void setDone(int index);
	
	public void setActive(int start, int end);
	public void setInactive(int start, int end);
	public void setDone(int start, int end);
	
}
