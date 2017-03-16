package job;

import java.util.Queue;

import threads.ThreadPool;
import util.KeyValue;

public class MultiThreadedSolution extends Solution {
	int numThreads;
	int stallCount;
	ThreadPool pool;
	public MultiThreadedSolution(String name, int stallCount, int numThreads) {
		super(name, stallCount);
		this.numThreads = numThreads;
		this.stallCount = stallCount;
	}
	
	@Override
	protected void prepare() {
		pool = new ThreadPool(numThreads, stallCount, this);
	}

	@Override
	protected void feed(String item) {
		pool.feed(item);
	}
	
	@Override
	protected KeyValue process() {
		KeyValue result = pool.run();
		return result;
	}
}
