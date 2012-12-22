package nayuki.sortdemo;

import java.util.Random;


// A collection of well known methods that are not specific to this application.
final class Utils {
	
	private static Random random = new Random();
	
	
	// Knuth shuffle
	public static void shuffle(int[] values) {
		for (int i = values.length - 1; i >= 0; i--) {
			int j = random.nextInt(i + 1);
			int temp = values[i];
			values[i] = values[j];
			values[j] = temp;
		}
	}
	
	
	// Sleeps without throwing InterruptedException
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new StopException();
		}
	}
	
}
