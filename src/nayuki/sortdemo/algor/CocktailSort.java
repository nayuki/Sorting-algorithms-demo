package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class CocktailSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		int left = 0;
		int right = array.length();
		int i = left;
		int dir = 1;
		while (left != right) {
			if (dir == 1) {
				if (i + 1 == right) {
					array.setDone(i);
					dir = -dir;
					right--;
				} else {
					array.compareAndSwap(i, i + 1);
				}
			} else {
				if (i == left) {
					array.setDone(i);
					dir = -dir;
					left++;
				} else {
					array.compareAndSwap(i - 1, i);
				}
			}
			i += dir;
		}
	}
	
	
	public String getName() {
		return "Coctail sort";
	}
	
}
