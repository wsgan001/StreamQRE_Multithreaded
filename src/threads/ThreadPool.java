package threads;

import java.util.ArrayList;
import java.util.Queue;

import job.MultiThreadedSolution;
import util.Hasher;
import util.KeyValue;
import util.MaxOcc;

public class ThreadPool {
	
	int size;
	ArrayList<String> items;
	boolean isStalling;
	WorkerThread[] threads;
	ArrayList<KeyValue> aggregator;
	MultiThreadedSolution sol;
	
	
	KeyValue curResult = new KeyValue("placeholder", -1000);
	
	public ThreadPool(int size, boolean isStalling, MultiThreadedSolution sol) {
		this.size = size;
		this.items = new ArrayList<String>();
		this.isStalling = isStalling;
		this.threads = new WorkerThread[size];
		this.aggregator = new ArrayList<KeyValue>();
		this.sol = sol;
		for (int i = 0; i < size; i++) {
			threads[i] = new WorkerThread(i, size, items, isStalling, this);
		}
	}
	
	public void addToAggregator(KeyValue result) {
		synchronized(aggregator) {
			aggregator.add(result);
			if (result.getValue() > curResult.getValue()) {
				curResult = result;
			}
			if (aggregator.size() == size) {
				this.sol.done(curResult);
			}
		}
	}
	
	public void run() {
		for (int i = 0; i < size; i++) {
			WorkerThread t = threads[i];
			t.start();
		}
	}
	
	public void feed(String item) {
		items.add(item);
	}
	
	
}
