package job;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	String name;
	boolean isStalling;
	public Solution(String name, boolean isStalling) {
		this.name = name;
		this.isStalling = isStalling;
	}
	
	public void solve(Queue<String> buffer) {
		long startTime = System.currentTimeMillis();
		while(!buffer.isEmpty()) {
			this.feed(buffer.poll());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Took: " + (endTime - startTime) + " ms.");
	}
	
	protected void feed(String item) {
	}
}
