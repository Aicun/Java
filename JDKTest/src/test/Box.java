package test;

import java.util.Arrays;
import java.util.List;

public class Box<T> {
	 private T t;          

	    public void set(T t) {
	        this.t = t;
	    }

	    public T get() {
	        return t;
	    }

	    public <U extends Number> void inspect(U u){
	        System.out.println("T: " + t.getClass().getName());
	        System.out.println("U: " + u.getClass().getName());
	    }

	    public static void printList(List<?> list) {
	        for (Object elem: list)
	            System.out.print(elem + " ");
	        System.out.println();
	    }
	    
	    public static void main(String[] args) {
	    	String a = "aa";
	    	Object o = a;
	    	String b = (String) o;
	    	System.out.println(b);
	    }
}
