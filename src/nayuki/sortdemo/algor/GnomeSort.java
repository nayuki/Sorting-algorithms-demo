package nayuki.sortdemo.algor;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


public final class GnomeSort extends SortAlgorithm {
	
	public void sort(SortArray array) {
		array.setInactive(0, array.length());
		for (int i = 0; i < array.length() - 1; ) {
			if (!array.compareAndSwap(i, i + 1) || i == 0)
				i++;
			else
				i--;
		}
		array.setDone(0, array.length());
	}
	
	
	public String getName() {
		return "Gnome sort";
	}
	
}
