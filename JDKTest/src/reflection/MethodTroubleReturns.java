package reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodTroubleReturns {

	private void drinkMe(int liters) {
		if (liters < 0)
			throw new IllegalArgumentException(
					"I can't drink a negative amount of liquid");
	}
	
	public static void main(String args[]){
		MethodTroubleReturns mrt = new MethodTroubleReturns();
		Class<?> c = mrt.getClass();
		try {
			Method m = c.getDeclaredMethod("drinkMe", int.class);
			m.invoke(mrt, -1);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
