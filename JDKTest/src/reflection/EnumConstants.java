package reflection;

import java.util.Arrays;

enum Eon {
	HADEAN, ARCHAEAN, PROTEROZOIC, PHANEROZOIC
}

public class EnumConstants {
	public static void main(String... args) {
		Class<?> c = Eon.class;
		Eon[] o = (Eon[]) c.getEnumConstants();
		System.out.format("Enum name:  %s%nEnum constants:  %s%nisEnum : %s%n", c.getName(),
				Arrays.asList(o),c.isEnum());
	}
}
