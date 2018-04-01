package com.xiuye.jdk.issue;

public class ReturnFinally {

	public static void main(String[] args) {
		log(f());
	}

	public static String f() {
		try {
			log("try");
			return "return try";
		} catch (Exception e) {

		}
		finally{
			log("finally");
			return "return finally";
		}

	}
	public static<T> void log(T t){
		System.out.println(t);
	}

}
