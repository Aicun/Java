package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokeMain {

	public static void main(String args[]){
		try {
			Class<?> c = Class.forName("reflection.Deet");
			Class[] argTypes = new Class[] {String[].class};
			
			Method main = c.getDeclaredMethod("main", argTypes);
			String[] mainArgs = Arrays.copyOfRange(args, 0, args.length);
			System.out.format("invoking %s.main()%n", c.getName());
		    main.invoke(null, (Object)mainArgs);
			c.getDeclaredMethod("main", argTypes);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
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
