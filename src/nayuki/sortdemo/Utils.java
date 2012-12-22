package nayuki.sortdemo;

import java.util.Random;


public final class Utils {
	
	public static final Random random = new Random();
	
	
	// Knuth shuffle
	public static void shuffle(int[] values) {
		for (int i = values.length - 1; i >= 0; i--) {
			int j = random.nextInt(i + 1);
			int temp = values[i];
			values[i] = values[j];
			values[j] = temp;
		}
	}
	
}
