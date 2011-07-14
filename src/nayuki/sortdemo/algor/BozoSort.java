package nayuki.sortdemo.algor;

import java.util.Random;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;


/**
 * Sorts by randomly selecting two elements and swapping them if they are in inverted order, until the array is sorted. The time complexity is in <var>O</var>(<var>n</var><sup>3</sup> log <var>n</var>).
 */
public class BozoSort extends SortAlgorithm {
	
	private static Random random = new Random();
	
	
	public String getName() {
		return "Bozo sort";
	}
	
	
	public void sort(SortArray array) {
		while (!array.isSorted()) {
			int i = random.nextInt(array.length());
			int j = random.nextInt(array.length());
			array.compareAndSwap(Math.min(i, j), Math.max(i, j));
		}
		array.setDone(0, array.length());
	}
	
}
