package nayuki.sortdemo;


final class SortThread extends Thread {
	
	private SortAlgorithm algorithm;
	private VisualSortArray array;
	
	
	public SortThread(SortAlgorithm algo, VisualSortArray array) {
		this.algorithm = algo;
		this.array = array;
	}
	
	
	public void run() {
		try {
			algorithm.sort(array);
			try {
				array.assertSorted();
				System.out.printf("%s: %d comparisons, %d swaps%n", algorithm.getName(), array.getComparisonCount(), array.getSwapCount());
			} catch (AssertionError e) {
				System.out.printf("%s: Sorting failed%n", algorithm.getName());
			}
		} catch (StopException e) {}
	}
	
	
	public void requestStop() {
		interrupt();
		array.requestStop();
	}
	
}
