package job;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Stream implements Iterator<String> {
    private static final String characters = "abcdefghijklmnopqrstuvwxyz";
    private final long N;
    private long index;
    private char[] text;
    private int length;
    private Random rng;
    
    public Stream(long seed, int len, long N) {
        assert N >= 1;
        this.N = N;
        index = 0;
        assert len >= 1;
        text = new char[len];
        rng = new Random(seed);
        length = len;
    }
    
    @Override
    public boolean hasNext() {
        return index < N;
    }

    @Override
    public String next() {
        //System.out.println("index: " + index);
        if (index >= N) {
            throw new NoSuchElementException();
        }
        
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        
        index += 1;
        
        if (index == N) {
        	return "done";
        }
        return new String(text);
    }
    
    public static void main(String[] args) {
        long N = 1_000; // stream length
        long seed = 1001L;
        int length = 2;
        Stream stream = new Stream(seed, length, N);
        while (stream.hasNext()) {
            String x = stream.next();
            System.out.println(x);
        }
    }
}