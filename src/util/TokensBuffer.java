package util;

import java.util.ArrayList;

public class TokensBuffer {
	ArrayList<String[]> blocks;
	private final long blockSize;
	int curTokenIndex = 0;
	
	public TokensBuffer(long blockSize) {
		blocks = new ArrayList<String[]>();
		this.blockSize = blockSize;
		addArray();
	}
	
	private void addArray() {
		String[] arr = new String[(int)blockSize];
		blocks.add(arr);
	}
	
	public void add(String token) {
		String[] curBlock = blocks.get(blocks.size() - 1);
		curBlock[curTokenIndex] = token;
		curTokenIndex++;
		if (curTokenIndex == blockSize) {
			curTokenIndex = 0;
			addArray();
		}
	}
	
	public String get(long index) {
		int blockNo = (int) (index / blockSize);
		int blockIndex = (int) (index % blockSize);
		String[] block = blocks.get(blockNo);
		return block[blockIndex];
	}
}
