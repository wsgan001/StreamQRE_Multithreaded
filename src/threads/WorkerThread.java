package threads;

import java.util.ArrayList;

import util.KeyValue;
import util.MaxOcc;

public class WorkerThread extends Thread {
	private int threadNo;
	private int numThreads;
	private ArrayList<String> tokens;
	private boolean isRunning;
	private int curIndex = 0;
	private MaxOcc maxHelper;
	private boolean isStalling;
	private ThreadPool threadPool;
	
	public WorkerThread(int threadNo, int numThreads, ArrayList<String> tokens, boolean isStalling, ThreadPool threadPool) {
		this.threadNo = threadNo;
		this.numThreads = numThreads;
		this.tokens = tokens;
		this.isStalling = isStalling;
		this.isRunning = true;
		this.maxHelper = new MaxOcc();
		this.threadPool = threadPool;
	}
	
	public int getAdjustedHash(String token) {
		int hashCode = token.hashCode();
		int adjustedHash = hashCode % numThreads;
		return adjustedHash;
	}
	
	public void process(String token) {
		int adjustedHash = getAdjustedHash(token);
		if (adjustedHash == this.threadNo) {
			this.maxHelper.add(token, isStalling);
		}
	}
	
	public void run() {
//		System.out.println("Running: " + threadNo);
		while(this.isRunning) {
			try {
				if (curIndex < tokens.size()) {
					String curString = tokens.get(curIndex);
					if (curString.equals("done")) {
						KeyValue result = this.maxHelper.getMax();
						this.threadPool.addToAggregator(result);
						this.isRunning = false;
						break;
					}
					process(curString);
					curIndex++;
				}
			} catch(Exception e) {
				
			}
			
		}
	}
	
	
}
