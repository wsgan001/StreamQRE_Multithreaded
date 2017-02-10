package util;

import java.util.ArrayList;
import java.util.HashMap;

public class MaxOcc {
	HashMap<String, Integer> occurences = new HashMap<String, Integer>();
	int curMaxOcc = 0;
	String curMaxKey = "";
	
	public MaxOcc() {}
	
	private static void stall() {
//		double num = 123;
//		for (int i = 0; i < 200000; i++) {
//			num = (Math.log(num) * 13) + 100;
//		}
		try {
			Thread.sleep(0, 1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add(String s, boolean isStalling) {
		int curOcc = 1;
		if (occurences.containsKey(s)) {
			curOcc = occurences.get(s) + 1;
		}
		occurences.put(s, curOcc);
		if (curOcc > curMaxOcc) {
			curMaxOcc = curOcc;
			curMaxKey = s;
		}
		if (isStalling) {
			stall();
		}		
	}
	
	public KeyValue getMax() {
		KeyValue result = new KeyValue(curMaxKey, curMaxOcc);
		return result;
	}
	
	public String toString() {
		return "k";
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
