package threads;

import java.util.ArrayList;
import java.util.Queue;

import job.KeyValue;
import util.Hasher;
import util.MaxOcc;

public class ThreadPool {
	
	int size;
	ArrayList<String> items;
	boolean isStalling;
	WorkerThread[] threads;
	
	public ThreadPool(int size, boolean isStalling) {
		this.size = size;
		this.items = new ArrayList<String>();
		this.isStalling = isStalling;
		this.threads = new WorkerThread[size];
		for (int i = 0; i < size; i++) {
			threads[i] = new WorkerThread();
		}
	}
	
	public void run() {
		
	}
	
	
}
