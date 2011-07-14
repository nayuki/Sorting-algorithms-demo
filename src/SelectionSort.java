/**
 * Sorts by finding the smallest element in the unsorted subarray and swapping it to end of the sorted subarray.
 */
class SelectionSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		for (int i = 0; i < array.length(); i++) {
			int minindex = i;
			for (int j = i; j < array.length(); j++) {
				if (array.compare(j, minindex) < 0)
					minindex = j;
			}
			array.swap(i, minindex);
			array.setDone(i);
		}
	}
	
	
	public String getName() {
		return "Selection sort";
	}
	
}