package test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;


public class Test {

	public static void main(String args[]) {
		/*Class clazz = Test.class;
		Method[] mm = clazz.getMethods();
		System.out.println(Modifier.isFinal(clazz.getModifiers()));
		for(Method m :mm){
			System.out.println(m.getName()+":"+Modifier.isFinal(clazz.getModifiers()));
		}*/
		
		String[] s = {"aa","bb"};
		Locale ll[] = {new Locale(""),new Locale("")};
		System.out.println(Locale.class.isAssignableFrom(ll[0].getClass()));
	}
}
