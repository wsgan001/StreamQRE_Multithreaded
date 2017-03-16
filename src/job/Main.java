package job;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import results.Result;
import util.StringGenerator;


public class Main {
	static int NUM_ITEMS = 500;
	static int NUM_THREADS = 8;
	static int NUM_TESTS = 100;
	static int STALL_COUNT = 300;
	static boolean IS_TESTING_SINGLE_THREAD = true;
	static boolean IS_TESTING_MULTI_THREAD = true;
	static boolean IS_REPORTING_STRING_GENERATION = true;
	static boolean IS_REPORTING_NUM_THREADS = false;
	static boolean IS_REPORTING_STALLING = false;
	static boolean IS_REPORTING_SPEEDUP = false;
	
	static ArrayList<Solution> solutions = new ArrayList<Solution>();
	static Queue<String> originalItems;
	static double[][] results;
	static Result result;

	public static void main(String[] args) {
		setup();
		saveMetaData();
		runTests();
		showResults();
		saveResults();
//		System.out.println("Testing with " + NUM_ITEMS + " items. \n");
//		if (IS_STALLING && IS_REPORTING_STALLING) {
//			System.out.println("With stalling");
//		}
		
	}
	
	private static void setup() {
		if (IS_TESTING_SINGLE_THREAD) {
			Solution sts = new SingleThreadedSolution("Single Threaded Solution", STALL_COUNT);
			solutions.add(sts);
		}
		if (IS_TESTING_MULTI_THREAD) {
			Solution mts = new MultiThreadedSolution("Multi Threaded Solution", STALL_COUNT, NUM_THREADS);
//			if (IS_REPORTING_NUM_THREADS) {
//				System.out.println("With " + NUM_THREADS + " thread(s).");
//			}
			solutions.add(mts);
		}
		
		long startTime = System.currentTimeMillis();
		originalItems = generateItems();
		long endTime = System.currentTimeMillis();
		if (IS_REPORTING_STRING_GENERATION) {
			System.out.println("String Generation Took: " + (endTime - startTime) + " ms. \n");
		}
		
		result = new Result(NUM_TESTS);
	}
	
	
	private static void runTests() {
		
		for (int i = 0; i < NUM_TESTS; i++) {
			System.out.println("Running test: " + i);
			for (int j = 0; j < solutions.size(); j++) {
				Queue<String> curProblem = new LinkedList<String>(originalItems);
				Solution curSolution = solutions.get(j);
				curSolution.solve(curProblem);
				String name = curSolution.getName();
				String key = name + " - duration";
				Long duration = new Long(curSolution.getDuration());
			    double durationDouble = duration.doubleValue();
				result.addData(i, key, durationDouble);
			}
			
//			if (IS_TESTING_SINGLE_THREAD && IS_TESTING_MULTI_THREAD && IS_REPORTING_SPEEDUP) {
//				long singleTime = sts.getDuration();
//				long multiTime = mts.getDuration();
//				long speedUp = singleTime / multiTime;
//				System.out.println("Speedup: x" + speedUp);
//			}
			
		}
	}
	
	private static void showResults() {
		result.print();
	}
	
	private static void saveResults() {
		result.save();
	}
	
	private static void saveMetaData() {
		result.addMetaData("num items", Integer.toString(NUM_ITEMS));
		String stallCountString = Integer.toString(STALL_COUNT);
		result.addMetaData("stall count", stallCountString);
		result.addMetaData("num threads", Integer.toString(NUM_THREADS));
		result.addMetaData("num tests", Integer.toString(NUM_TESTS));
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
