package job;

import util.KeyValue;
import util.MaxOcc;

public class SingleThreadedSolution extends Solution {
	
	MaxOcc maxHelper;
	
	public SingleThreadedSolution(String name, int stallCount) {
		super(name, stallCount);
	}
	
	@Override
	protected void prepare() {
		maxHelper = new MaxOcc();
	}
	
	@Override
	protected void feed(String item) {
		maxHelper.add(item, stallCount);
	}
	
	@Override
	protected KeyValue process() {
		KeyValue result = maxHelper.getMax();
		return result;
	}
}


