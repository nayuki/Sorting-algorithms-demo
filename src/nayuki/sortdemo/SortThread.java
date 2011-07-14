package nayuki.sortdemo;


public class SortThread extends Thread {
	
	private SortAlgorithm algorithm;
	private SortArray array;
	
	
	public SortThread(SortAlgorithm algor, SortArray array) {
		this.algorithm = algor;
		this.array = array;
	}
	
	
	public void run() {
		try {
			algorithm.sort(array);
			if (array.isSorted())
				System.out.printf("%s: %d comparisons, %d swaps%n", algorithm.getName(), array.getComparisons(), array.getSwaps());
			else
				System.out.printf("%s: Sorting failed%n", algorithm.getName());
		} catch (NullPointerException e) {}
	}
	
	
	public void stop1() {
		array.destroy();
	}
	
}
