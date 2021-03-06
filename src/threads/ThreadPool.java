package threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import job.MultiThreadedSolution;
import util.KeyValue;
import util.TokensBuffer;

public class ThreadPool {
	
	private static final int BUFFER_BLOCK_SIZE = 1024;
	
	int size;
	int stallCount;
	WorkerThread[] threads;
	ArrayList<KeyValue> aggregator;
	MultiThreadedSolution sol;
	
	KeyValue curResult = new KeyValue("placeholder", -1000);
	TokensBuffer buffer = new TokensBuffer(BUFFER_BLOCK_SIZE);
	public ThreadPool(int size, int stallCount, MultiThreadedSolution sol) {
		this.size = size;
		this.stallCount = stallCount;
		this.threads = new WorkerThread[size];
		this.aggregator = new ArrayList<KeyValue>();
		this.sol = sol;
		for (int i = 0; i < size; i++) {
			threads[i] = new WorkerThread(i, size, stallCount, buffer, this);
		}
	}
	
	public void addToAggregator(KeyValue result) {
		synchronized(aggregator) {
			aggregator.add(result);
			if (result.getValue() > curResult.getValue()) {
				curResult = result;
			}
			if (aggregator.size() == size) {
				aggregator.notifyAll();
			}
		}
	}
	
	public KeyValue run() {
		for (int i = 0; i < size; i++) {
			WorkerThread t = threads[i];
			t.start();
		}
		synchronized(aggregator) {
			try {
				aggregator.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return curResult;
	}
	
	public void feed(String item) {
		buffer.add(item);
	}
	
	
}
