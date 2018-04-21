package reflection;

import static java.lang.System.out;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodSpy {

	 private static final String  fmt = "%24s: %s%n";
	 
	 <E extends RuntimeException> void genericThrow() throws E {}
	 
	 public static void main(String arg[]){
		 
		 try {
			Class clazz = Class.forName("reflection.MethodSpy");
			
			Method[] methods = clazz.getDeclaredMethods();
			
			for(Method method:methods){
				out.format("%s%n", method.toGenericString());

				out.format(fmt, "ReturnType", method.getReturnType());
				out.format(fmt, "GenericReturnType", method.getGenericReturnType());
				
				Class<?>[] pTypes = method.getParameterTypes();
				Type[] gpTypes = method.getGenericParameterTypes();
				
				for (int i = 0; i < pTypes.length; i++) {
				    out.format(fmt,"ParameterType", pTypes[i]);
				    out.format(fmt,"GenericParameterType", gpTypes[i]);
				}
				
				Class[] eTypes = method.getExceptionTypes();
				Type[] gxType = method.getGenericExceptionTypes();
				for (int i = 0; i < eTypes.length; i++) {
				    out.format(fmt,"ExceptionType", eTypes[i]);
				    out.format(fmt,"GenericExceptionType", gxType[i]);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
	 }
}
