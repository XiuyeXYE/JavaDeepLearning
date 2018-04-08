package com.xiuye.jdk.issue;

public class VerifyBoxInOut {

	public static void main(String[] args) {

		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		log(c == d);
		log(e==f);
		log(c == (a+b));
		log(c.equals(a+b));
		log(g == (a+b));
		log(g.equals(a+b));
	}
	public static<T> void log(T t){
//		T t = new T();
		System.out.println(t);
	}

}
