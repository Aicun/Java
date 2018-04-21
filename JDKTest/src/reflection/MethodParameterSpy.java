package reflection;

import static java.lang.System.out;

import java.lang.reflect.*;

public class MethodParameterSpy {
	 private static final String  fmt = "%24s: %s%n";
	 
	 public static void printClassConstructors(Class c){
		 Constructor[] constructors = c.getConstructors();
		 out.format(fmt, "Number of Constructors",constructors.length);
		 for(Constructor constructor:constructors){
			 //printConstructors
		 }
		 
		 Constructor[] declaredCon = c.getDeclaredConstructors();
		 out.format(fmt, "Number of declared constructors",declaredCon.length);
		 for(Constructor constructor:declaredCon){
			 //printConstructors
		 }
	 }
	 
	 public static void printClassMethods(Class c){
		 Method[] methods = c.getMethods();
		 out.format(fmt, "Number of methods", methods.length);
		 for(Method method : methods){
			 //printMethods
			 //printMethodParameters
		 }
	 }
	 
	 public static void printConstructor(Constructor  constructor){
		 out.format("%s%n", constructor.toGenericString());
	 }
}
