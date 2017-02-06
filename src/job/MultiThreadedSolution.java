package job;

import java.util.Queue;

import threads.ThreadPool;

public class MultiThreadedSolution extends Solution {
	int numThreads;
	boolean isStalling;
	ThreadPool pool;
	public MultiThreadedSolution(String name, boolean isStalling, int numThreads) {
		super(name, isStalling);
		this.numThreads = numThreads;
		this.isStalling = isStalling;
		pool = new ThreadPool(numThreads, isStalling);
	}

	@Override
	protected void feed(String item) {
		
	}
}
