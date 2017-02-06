package util;


public class Hasher {
	int size;
	public Hasher(int size) {
		this.size = size;
	}
	
	public int classify(String key) {
		return key.hashCode() % this.size;
	}
	
	public static void main(String[] args) {
		Hasher h = new Hasher(5);
		h.classify(StringGenerator.generate());
	}
}
