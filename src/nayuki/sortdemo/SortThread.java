package nayuki.sortdemo;


public final class SortThread extends Thread {
	
	private SortAlgorithm algorithm;
	private SortArray array;
	
	
	public SortThread(SortAlgorithm algor, SortArray array) {
		this.algorithm = algor;
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
	
	
	public void stop1() {
		interrupt();
		array.stop();
	}
	
}
