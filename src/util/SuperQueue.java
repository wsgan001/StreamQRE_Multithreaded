package util;

import java.util.LinkedList;
import java.util.Queue;

public class SuperQueue {

	private Queue<String> firstQueue = new LinkedList<String>();
	private Queue<String> secondQueue = new LinkedList<String>();
	Queue<String> readQueue = firstQueue;
	Queue<String> writeQueue = secondQueue;
	Object lock = new Object();
	
	public void add(String s) {
		synchronized(writeQueue) {
			writeQueue.add(s);
		}
		synchronized(lock) {
			lock.notify();
		}
	}
	
	private void switchQueues() {
		synchronized(writeQueue) {
			Queue<String> tempWrite = writeQueue;
			writeQueue = readQueue;
			readQueue = tempWrite;
		}
	}
	
	public String take() throws InterruptedException {
		if (readQueue.isEmpty()) {
			switchQueues();
		}
		if (readQueue.isEmpty()) {
			synchronized(lock) {
				lock.wait();
			}
			switchQueues();
		}
		return readQueue.poll();
	}
	
}
