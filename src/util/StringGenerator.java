package util;

import java.util.HashMap;
import java.util.Random;

public class StringGenerator {
	public static String generate() {
		Random rng = new Random();
		int length = 2;
		String characters = "abcdefghijklmnopqrstuvwxyz";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}
	
	public static void main(String args[]) {
		HashMap<String, Integer> nums = new HashMap<String, Integer>();
		for (int i = 0; i < 1000; i++) {
			String s = StringGenerator.generate();
			int occ = 1;
			if (nums.containsKey(s)) {
				occ = nums.get(s) + 1;
			}
			nums.put(s, occ);
		}
		System.out.println(nums);
	}
}
