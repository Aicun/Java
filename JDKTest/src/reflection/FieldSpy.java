package reflection;

import java.lang.reflect.Field;
import java.util.List;

public class FieldSpy<T> {
	public boolean [][] b = {{false,true},{false,false}};
	public int i = 0;
	public List<Integer> list;
	public T t;
	private String s;
	
	public static void main(String args[]){
		try {
			Class<?> clazz = Class.forName("reflection.FieldSpy");
			Field[] fs = clazz.getDeclaredFields();
			
			for(Field f : fs){
				System.out.format("Type: %s%n", f.getType());
				System.out.format("GenericType: %s%n", f.getGenericType());
				System.out.println("################");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
