package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class CocktailSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		int left = 0;
		int right = array.length();
		int i = left;
		while (left < right) {
			// Scan right
			for (; i + 1 < right; i++)
				array.compareAndSwap(i, i + 1);
			array.setDone(i);
			right--;
			i--;
			if (left == right)
				break;
			
			// Scan left
			for (; i > left; i--)
				array.compareAndSwap(i - 1, i);
			array.setDone(i);
			left++;
			i++;
		}
	}
	
	
	public String getName() {
		return "Cocktail sort";
	}
	
}
