package com.xiuye.jvm.issue;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class StringVerify {

	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		Field field = null;

		field = String.class.getDeclaredField("value");
		field.setAccessible(true);

		System.out.println(field);

		// int modifierFlag = field.getModifiers();
//		Field fieldField = Field.class.getDeclaredField("modifiers");
//		fieldField.setAccessible(true);

		char[] arr = new char[] { 'A', 'B', 'C' };

		String s = new String(arr);
		System.out.println(s);
		System.out.println(s.hashCode());
		System.out.println(s.toCharArray().hashCode());

		System.out.println("================================");

		String sc = new String(s);
		System.out.println(s);
		System.out.println(s.hashCode());
		System.out.println(s.toCharArray().hashCode());
		System.out.println(sc);
		System.out.println(sc.hashCode());
		System.out.println(sc.toCharArray().hashCode());


		System.out.println("================================");
		arr[0] = 'Z';
		if (field != null) {
//			fieldField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.set(s, new char[]{'X','Y','Z'});
		}
		System.out.println(s);
		System.out.println(s.hashCode());
		System.out.println(s.toCharArray().hashCode());
		System.out.println(sc);
		System.out.println(sc.hashCode());
		System.out.println(sc.toCharArray().hashCode());
		System.out.println(s.toCharArray()[0]);
		System.out.println(sc.toCharArray()[0]);
		System.out.println(s == sc);
		System.out.println(s.toCharArray() == sc.toCharArray());

	}

}
