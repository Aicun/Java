package reflection;

import static java.lang.System.out;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Locale;

public class Deet<T> {
	private boolean testDeet(Locale l) {
		// getISO3Language() may throw a MissingResourceException
		out.format("Locale = %s, ISO Language Code = %s%n", l.getDisplayName(),
				l.getISO3Language());
		return true;
	}

	private int testFoo(int l) {
		return l;
	}

	private boolean testBar() {
		return true;
	}
	
	public static void main(String args[]){
		try {
			Class c = Class.forName("reflection.Deet");
			Object o = c.newInstance();
			Method[] methods = c.getDeclaredMethods();
			for(Method m :methods){
				Type[] pTypes = m.getGenericParameterTypes();
				//m.setAccessible(true);
				String name = m.getName();
				
				out.format("%s() modifier %s%n",name,Modifier.toString(m.getModifiers()));
				if(pTypes.length == 0) {
					Object ret = m.invoke(o);
					out.format("%s() returned %b%n",name,(boolean)ret);
				}else {
					if(m.getGenericReturnType() == boolean.class) {
						Object ret = m.invoke(o, new Locale("zh","ZH"));
						out.format("%s() returned %b%n", name, (Boolean) ret);
					}
					if(m.getGenericReturnType() == int.class) {
						Object ret = m.invoke(o,2);
						out.format("%s() returned %d%n", name, (int)ret);
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
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
