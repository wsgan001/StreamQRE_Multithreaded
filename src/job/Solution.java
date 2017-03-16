package job;

import java.util.LinkedList;
import java.util.Queue;

import util.KeyValue;

public class Solution {
	String name;
	int stallCount;
	long startTime;
	long endTime;
	
	public Solution(String name, int stallCount) {
		this.name = name;
		this.stallCount = stallCount;
	}
	
	public String getName() {
		return this.name;
	}
	
	public KeyValue solve(Queue<String> buffer) {
		startTime = System.currentTimeMillis();
		prepare();
		while(!buffer.isEmpty()) {
			this.feed(buffer.poll());
		}
		KeyValue result = this.process();
		this.done();
		return result;
	}
	
	protected KeyValue process() {
		return null;
	}
	
	protected void prepare() {

	}
	
	protected void feed(String item) {
	}
	
	public void done() {
		endTime = System.currentTimeMillis();
	}
	
	public long getDuration() {
		return endTime - startTime;
	}
}
