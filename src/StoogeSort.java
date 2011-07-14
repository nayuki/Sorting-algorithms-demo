/**
 * A silly but simple, recursive sorting algorithm. The time complexity is in <var>O</var>(<var>n</var><sup>2.71</sup>).
 */
class StoogeSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		sort(array, 0, array.length());
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Stooge sort";
	}
	
	
	private static void sort(SortArray array, int start, int end) {
		array.setActive(start, end);
		array.compareAndSwap(start, end - 1);
		array.setInactive(start, end);
		int length = end - start;
		if (length < 3)
			return;
		
		int third = length / 3;
		sort(array, start, end - third);
		sort(array, start + third, end);
		sort(array, start, end - third);
	}
	
}