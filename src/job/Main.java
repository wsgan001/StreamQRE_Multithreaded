package job;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import results.Result;

public class Main {
    static int strLength = 2;
    static long seed = 1001L;
	static long NUM_ITEMS = 1000;
	static int NUM_THREADS = 8;
	static int NUM_TESTS = 5;
	static int STALL_COUNT = 0;
	static boolean IS_TESTING_SINGLE_THREAD = true;
	static boolean IS_TESTING_MULTI_THREAD = true;
	
	static ArrayList<Solution> solutions = new ArrayList<Solution>();
	static double[][] results;
	static Result result;

	public static void main(String[] args) {
		setup();
		saveMetaData();
		runTests();
		showResults();
		saveResults();
	}
	
	private static void setup() {
		if (IS_TESTING_SINGLE_THREAD) {
			Solution sts = new SingleThreadedSolution("Single Threaded Solution", STALL_COUNT);
			solutions.add(sts);
		}
		if (IS_TESTING_MULTI_THREAD) {
			Solution mts = new MultiThreadedSolution("Multi Threaded Solution", STALL_COUNT, NUM_THREADS);
			solutions.add(mts);
		}
		
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		
		result = new Result(NUM_TESTS);
	}
	
	
	private static void runTests() {
		
		for (int i = 0; i < NUM_TESTS; i++) {
			System.out.println("Running test: " + i);
			for (int j = 0; j < solutions.size(); j++) {
				Stream curProblem = new Stream(seed, strLength, NUM_ITEMS);
				//System.out.println("solution index: " + j);
				Solution curSolution = solutions.get(j);
				curSolution.solve(curProblem);
				String name = curSolution.getName();
				String key = name + " - duration";
				Long duration = new Long(curSolution.getDuration());
			    double durationDouble = duration.doubleValue();
				result.addData(i, key, durationDouble);
			}			
		}
	}
	
	private static void showResults() {
		result.print();
	}
	
	private static void saveResults() {
		result.save();
	}
	
	private static void saveMetaData() {
		result.addMetaData("num items", Long.toString(NUM_ITEMS));
		String stallCountString = Integer.toString(STALL_COUNT);
		result.addMetaData("stall count", stallCountString);
		result.addMetaData("num threads", Integer.toString(NUM_THREADS));
		result.addMetaData("num tests", Integer.toString(NUM_TESTS));
	}
}
