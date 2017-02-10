package job;

import java.util.LinkedList;
import java.util.Queue;

import util.KeyValue;

public class Solution {
	String name;
	boolean isStalling;
	long startTime;
	long endTime;
	
	public Solution(String name, boolean isStalling) {
		this.name = name;
		this.isStalling = isStalling;
	}
	
	public void solve(Queue<String> buffer) {
		startTime = System.currentTimeMillis();
		while(!buffer.isEmpty()) {
			this.feed(buffer.poll());
		}
	}
	
	protected void feed(String item) {
	}
	
	public void done(KeyValue result) {
		endTime = System.currentTimeMillis();
		System.out.println(this.name + " - Took: " + (endTime - startTime) + " ms.");
		System.out.print("Result: ");
		result.print();
	}
	
	public long getDuration() {
		return endTime - startTime;
	}
}
