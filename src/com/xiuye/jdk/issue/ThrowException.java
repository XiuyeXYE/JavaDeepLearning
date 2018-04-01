package com.xiuye.jdk.issue;

class A extends Exception {

}

class B extends A {

}

public class ThrowException {

	public static void main(String[] args) {

		try {
			try {
				log("try");
				throw new B();
			} catch (A a) {
				log("catch A");
				throw a;
			}
		} catch (B b) {
			log("catch B");
		}

	}

	public static <T> void log(T t) {
		System.out.println(t);
	}

}
