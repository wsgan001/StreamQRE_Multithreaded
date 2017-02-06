package job;

import util.MaxOcc;

public class SingleThreadedSolution extends Solution {
	
	MaxOcc maxHelper = new MaxOcc();
	
	public SingleThreadedSolution(String name, boolean isStalling) {
		super(name, isStalling);
	}
	
	@Override
	protected void feed(String item) {
		maxHelper.add(item, isStalling);
	}
}
