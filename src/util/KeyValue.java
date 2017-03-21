package util;

public class KeyValue {
	String key;
	int value;
	public KeyValue(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	
	public int getValue() {
		return value;
	}
	
	public void print() {
		System.out.println(this.key + ": " + this.value);
	}
}
