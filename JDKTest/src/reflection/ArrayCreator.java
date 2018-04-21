package reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayCreator {

	private  static String s = "java.math.BigInteger bi[] = { 123, 234, 345 }";
	private static Pattern p =  Pattern.compile("^\\s*(\\S+)\\s*\\w+\\[\\].*\\{\\s*([^}]+)\\s*\\}");
	
	public static void main(String args[]) {
		Matcher m = p.matcher(s);
		if(m.matches()){
			String cname = m.group(1);
			String[] cVals = m.group(2).split("[\\s,]+");
			int n = cVals.length;
			
			try {
				Class<?> c = Class.forName(cname);
				Object o = Array.newInstance(c, n);
				
				for(int i=0;i<n;i++){
					String v = cVals[i];
					Constructor ctor = c.getConstructor(String.class);
					Object val = ctor.newInstance(v);
					Array.set(o, i, val);
				}
				 Object[] oo = (Object[])o;
	             System.out.format("%s[] = %s%n", cname, Arrays.toString(oo));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
