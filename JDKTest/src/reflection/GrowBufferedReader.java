package reflection;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class GrowBufferedReader {
	private static final int srcBufSize  = 1024;
	private static char[] src = new char[srcBufSize];
	static{
		src[srcBufSize - 1] = 'x';
	}
	
	private static  CharArrayReader car = new CharArrayReader(src);
	
	public static void main(String args[]){
		try {
			BufferedReader br = new BufferedReader(car);
			
			Class<?> c = br.getClass();
			Field f = c.getDeclaredField("cb");
			
			f.setAccessible(true);
			
			char[] cbVal = char[].class.cast(f.get(br));
			
			char[] newVal = Arrays.copyOf(cbVal, cbVal.length * 2);
			
			f.set(br, newVal);
			
			for (int i = 0; i < srcBufSize; i++)
					br.read();
			System.out.println(cbVal.length);
			System.out.format("Using new backing array, size=%d%n", newVal.length);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
