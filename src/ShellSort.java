/**
 * Sorts by running a sequence of insertion sorts with varying gaps. The time complexity is in <var>O</var>(<var>n</var><sup>1.3</sup>).
 */
class ShellSort extends SortAlgorithm {
	
	private static int[] gapSequence = {701, 301, 132, 57, 23, 10, 4, 1};
	
	
	public void sort(SortArray array) {
		for (int h : gapSequence) {
			array.setInactive(0, array.length());
			
			// Do an insertion sort with a step size of h
			for (int j = 0; j < array.length(); j++) {
				for (int k = j; k >= h && array.compareAndSwap(k - h, k); k -= h);
			}
		}
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Shell sort";
	}
	
}