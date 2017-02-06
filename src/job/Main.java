package job;

import java.util.LinkedList;
import java.util.Queue;

import util.StringGenerator;


public class Main {
	static int NUM_ITEMS = 1000000;
	static int NUM_THREADS = 8;
	static int NUM_TESTS = 1;
	static boolean IS_STALLING = false;

	public static void main(String[] args) {
		System.out.println("Testing with " + NUM_ITEMS + " items. \n");
		if (IS_STALLING) {
			System.out.println("With stalling");
		}
		for (int i = 0; i < NUM_TESTS; i++) {
			long startTime = System.currentTimeMillis();
			Queue<String> items = generateItems();
			Queue<String> testItems1 = new LinkedList<String>(items);
			Queue<String> testItems2 = new LinkedList<String>(items);
			long endTime = System.currentTimeMillis();
			System.out.println("String Generation Took: " + (endTime - startTime) + " ms.");
			
			SingleThreadedSolution sts = new SingleThreadedSolution("Single Threaded Solution", IS_STALLING);
			sts.solve(testItems1);
//			MultiThreadedSolution mts = new MultiThreadedSolution("Multi Threaded Solution", IS_STALLING, NUM_THREADS);
//			System.out.println("With " + NUM_THREADS + " thread(s).");
//			mts.solve(testItems2);
//			System.out.println('\n');
		}
	}
	
	private static Queue<String> generateItems() {
		Queue<String> items = new LinkedList<String>();
		for (int i = 0; i < NUM_ITEMS; i++) {
			String s = StringGenerator.generate();
			items.add(s);
		}
		return items;
	}
}
