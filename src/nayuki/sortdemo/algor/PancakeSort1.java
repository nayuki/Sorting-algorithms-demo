package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class PancakeSort1 extends SortAlgorithm {
	
	public void sort(SortArray array) {
		for (int i = 1; i < array.length(); i++) {
			int j = i;
			while (j >= 1 && array.compare(j - 1, i) > 0)
				j--;
			flip(array, j, i);
			flip(array, j, i + 1);
		}
		array.setDone(0, array.length());
	}
	
	
	private static void flip(SortArray array, int start, int end) {
		int half = (end - start) / 2;
		for (int i = 0; i < half; i++)
			array.swap(start + i, end - 1 - i);
	}
	
	
	public String getName() {
		return "Pancake sort (insertion)";
	}
	
}
