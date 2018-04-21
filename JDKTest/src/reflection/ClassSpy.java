package reflection;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ClassSpy {
	static PrintStream out = System.out;

	public static void main(String args[]) {
		Class<?> c = null;
		try {
			c = Class.forName("java.lang.String");
			Package p = c.getPackage();
			out.format("Package:%n  %s%n%n", (p != null ? p.getName()
					: "-- No Package --"));
			
			 printMembers(c.getConstructors(), "Constructor");
			 printMembers(c.getFields(), "Fields");
			 printMembers(c.getMethods(), "Methods");
			 printClasses(c);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void printMembers(Member[] mem, String s) {
		out.format("%s:%n", s);
		for (Member m : mem) {
			if (m instanceof Field) {
				out.format(" %s%n", ((Field) m).toGenericString());
				 Field f = (Field)m;
				    out.format("  %s%n", f.toGenericString());
				    out.format("  -- declared in: %s%n", f.getDeclaringClass());
			} else if (m instanceof Constructor) {
				out.format(" %s%n", ((Constructor) m).toGenericString());
			} else if (m instanceof Method) {
				out.format(" %s%n", ((Method) m).toGenericString());
			}
		}
		if (mem.length == 0)
			out.format("  -- No %s --%n", s);
		out.format("%n");
	}

	private static void printClasses(Class<?> c) {
		out.format("Classes:%n");
		Class<?>[] clss = c.getClasses();
		for (Class<?> cls : clss)
			out.format("  %s%n", cls.getCanonicalName());
		if (clss.length == 0)
			out.format("  -- No member interfaces, classes, or enums --%n");
		out.format("%n");
	}
}
