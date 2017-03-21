package util;

import java.util.ArrayList;
import java.util.HashMap;

public class MaxOcc {
	HashMap<String, Integer> occurences = new HashMap<String, Integer>();
	int curMaxOcc = 0;
	String curMaxKey = "";
	
	static double stallingNum = 5.0;
	
	public MaxOcc() {}
	
	public static void stall(int stallCount) {
		for (int i = 0; i < stallCount; i++) {
			stallingNum = (1 + stallingNum) / stallingNum;
		}
	}
	
	public void add(String s, int stallCount) {
		int curOcc = 1;
		if (occurences.containsKey(s)) {
			curOcc = occurences.get(s) + 1;
		}
		occurences.put(s, curOcc);
		if (curOcc > curMaxOcc) {
			curMaxOcc = curOcc;
			curMaxKey = s;
		}
		stall(stallCount);		
	}
	
	public KeyValue getMax() {
		KeyValue result = new KeyValue(curMaxKey, curMaxOcc);
		return result;
	}

	
	public static KeyValue aggregateMax(ArrayList<KeyValue> candidates) {
		KeyValue curMax = new KeyValue(null, 0);
		for (KeyValue kv : candidates) {
			if (kv.getValue() > curMax.getValue()) {
				curMax = kv;
			}
		}
		return curMax;
	}
}
