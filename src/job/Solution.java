package job;

import util.KeyValue;
import util.Stream;

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
	
	public KeyValue solve(Stream stream) {
		startTime = System.currentTimeMillis();
		prepare();
		while (stream.hasNext()) {
			this.feed(stream.next());
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
