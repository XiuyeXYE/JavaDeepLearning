package com.xiuye.reflect;

import java.security.AccessController;
import java.security.PrivilegedAction;

import com.xiuye.util.LogUtil;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class ReflectionTest {
	public static void main(String[] args) {

		// f();
		// System.out.println(Reflection.getCallerClass());
		// h();
		A.f();
	}

	public static void h() {
		f();
	}

	public static void g(Class<?> clazz) {
		System.out.println(clazz);
	}

	public static void f() {
		LogUtil.log(AccessController.doPrivileged((PrivilegedAction<String>) () -> {
			g(Reflection.getCallerClass());
			return "OK";
		}));
	}

	static class A {
		@CallerSensitive
		public static void f() {
			ReflectionTest.f();
		}
	}

}
