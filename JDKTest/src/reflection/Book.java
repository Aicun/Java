package reflection;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.Arrays;

enum Tweedle {
	DEE, DUM
}

public class Book {

	public static PrintStream out = System.out;

	public long chapters = 0;
	public String[] characters = { "Alice", "John" };
	public Tweedle twin = Tweedle.DEE;

	public static void main(String args[]) {

		Book book = new Book();
		String fmt = "%6S:  %-12s = %s%n";

		try {
			Class<?> clazz = book.getClass();
			
			Field field = clazz.getDeclaredField("chapters");
			out.format(fmt, "before", "chapters", book.chapters);
			field.setLong(book, 1);
			out.format(fmt, "after", "chapters", book.chapters);

			Field chars = clazz.getDeclaredField("characters");
			out.format(fmt, "befer","characters",Arrays.asList(book.characters));
			String[] newChars = { "Queen", "King" };
			chars.set(book, newChars);
			out.format(fmt, "after","characters",Arrays.asList(book.characters));
			
			Field t = clazz.getDeclaredField("twin");
			out.format(fmt, "before", "twin", book.twin);
		    t.set(book, Tweedle.DUM);
		    out.format(fmt, "after", "twin", t.get(book));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
