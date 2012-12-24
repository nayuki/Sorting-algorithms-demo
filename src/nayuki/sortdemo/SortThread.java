package nayuki.sortdemo;


final class SortThread extends Thread {
	
	private SortAlgorithm algorithm;
	private VisualSortArray array;
	
	
	public SortThread(VisualSortArray array, SortAlgorithm algo) {
		this.array = array;
		this.algorithm = algo;
		new SortFrame(algorithm.getName(), array.getCanvas(), this);
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
