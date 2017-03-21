package threads;

import java.util.ArrayList;

import util.KeyValue;
import util.MaxOcc;
import util.TokensBuffer;

public class WorkerThread extends Thread {
	private int threadNo;
	private int numThreads;
	private boolean isRunning;
	private int curIndex = 0;
	private MaxOcc maxHelper;
	private int stallCount;
	private ThreadPool threadPool;
	private TokensBuffer buffer;
	
	public WorkerThread(int threadNo, int numThreads, int stallCount, TokensBuffer buffer, ThreadPool threadPool) {
		this.threadNo = threadNo;
		this.numThreads = numThreads;
		this.stallCount = stallCount;
		this.isRunning = true;
		this.maxHelper = new MaxOcc();
		this.buffer = buffer;
		this.threadPool = threadPool;
	}
	
	// (hash of token) % (number of threads)
	public int getAdjustedHash(String token) {
		int hashCode = token.hashCode();
		int adjustedHash = hashCode % numThreads;
		return adjustedHash;
	}
	
	public void process(String token) {
		int adjustedHash = getAdjustedHash(token);
		if (adjustedHash == this.threadNo) {
			this.maxHelper.add(token, stallCount);
		}
	}
	
	public void run() {
		while(this.isRunning) {
			String curString = "not yet";
			curString = buffer.get(curIndex);
			if (curString.equals("done")) {
				KeyValue result = this.maxHelper.getMax();
				this.threadPool.addToAggregator(result);
				this.isRunning = false;
				break;
			}
			process(curString);
			curIndex++;
		}
	}
	
	
}
