package com.xiuye.jvm.issue;

public class TryFinallyReturn {

	static class A extends Exception {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

	}

	public static String f() {
		try {
			throw new A();
//			return "try";
		} catch (Exception e) {
			return "exception";
		} finally {
//			return "finally";
		}
	}

	public static void h(boolean b) throws A{
		if(b)throw new A();
	}

	public static void main(String[] args) throws A {
//		System.out.println(f());
		h(true);
	}

}
