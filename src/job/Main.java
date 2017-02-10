package job;

import java.util.LinkedList;
import java.util.Queue;

import util.StringGenerator;


public class Main {
	static int NUM_ITEMS = 10000;
	static int NUM_THREADS = 13;
	static int NUM_TESTS = 1;
	static boolean IS_STALLING = true;
	static boolean IS_TESTING_SINGLE_THREAD = true;
	static boolean IS_TESTING_MULTI_THREAD = true;
	static boolean IS_REPORTING_STRING_GENERATION = true;
	static boolean IS_REPORTING_NUM_THREADS = false;
	static boolean IS_REPORTING_STALLING = false;
	static boolean IS_REPORTING_SPEEDUP = false;

	public static void main(String[] args) {
		System.out.println("Testing with " + NUM_ITEMS + " items. \n");
		if (IS_STALLING && IS_REPORTING_STALLING) {
			System.out.println("With stalling");
		}
		for (int i = 0; i < NUM_TESTS; i++) {
			long startTime = System.currentTimeMillis();
			Queue<String> items = generateItems();
			Queue<String> testItems1 = new LinkedList<String>(items);
			Queue<String> testItems2 = new LinkedList<String>(items);
			long endTime = System.currentTimeMillis();
			
			SingleThreadedSolution sts = null;
			MultiThreadedSolution mts = null;
			
			if (IS_REPORTING_STRING_GENERATION) {
				System.out.println("String Generation Took: " + (endTime - startTime) + " ms. \n");
			}
			
			if (IS_TESTING_SINGLE_THREAD) {
				sts = new SingleThreadedSolution("Single Threaded Solution", IS_STALLING);
				sts.solve(testItems1);
			}
			
			if (IS_TESTING_MULTI_THREAD) {
				mts = new MultiThreadedSolution("Multi Threaded Solution", IS_STALLING, NUM_THREADS);
				if (IS_REPORTING_NUM_THREADS) {
					System.out.println("With " + NUM_THREADS + " thread(s).");
				}
				mts.solve(testItems2);
				System.out.println('\n');
			}
			
			if (IS_TESTING_SINGLE_THREAD && IS_TESTING_MULTI_THREAD && IS_REPORTING_SPEEDUP) {
				long singleTime = sts.getDuration();
				long multiTime = mts.getDuration();
				long speedUp = singleTime / multiTime;
				System.out.println("Speedup: x" + speedUp);
			}
		}
	}
	
	private static Queue<String> generateItems() {
		Queue<String> items = new LinkedList<String>();
		for (int i = 0; i < NUM_ITEMS; i++) {
			String s = StringGenerator.generate();
			items.add(s);
		}
		items.add("done");
		return items;
	}
}
