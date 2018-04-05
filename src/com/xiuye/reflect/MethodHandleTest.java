package com.xiuye.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

	public static void main(String[] args) {

		MethodHandles.Lookup lookup = MethodHandles.lookup();
		try {
			MethodHandle mh = lookup.findVirtual(MethodHandleTest.class, "f", MethodType.methodType(void.class));
			mh.invoke(new MethodHandleTest());
			mh = lookup.findStatic(MethodHandleTest.class, "g", MethodType.methodType(void.class));
			mh.invoke();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public void f(){
		System.out.println("MethodHandleTest.f");
	}
	public static void g(){
		System.out.println("MethodHandleTest::g");
	}

}
