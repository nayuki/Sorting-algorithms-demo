package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class PancakeSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		for (int i = array.length() - 1; i >= 0; i--) {
			int maxIndex = 0;
			for (int j = 1; j <= i; j++) {
				if (array.compare(j, maxIndex) > 0)
					maxIndex = j;
			}
			flip(array, 0, maxIndex + 1);
			flip(array, 0, i + 1);
			array.setDone(i);
		}
	}
	
	
	private static void flip(SortArray array, int start, int end) {
		int half = (end - start) / 2;
		for (int i = 0; i < half; i++)
			array.swap(start + i, end - 1 - i);
	}
	
	
	public String getName() {
		return "Pancake sort";
	}
	
}
