package com.xiuye.JSR269;

public class Main {

	public static void main(String[] args) {
		log(new Dummy());
	}
	public static<T> void log(T t){
		System.out.println(t);
	}

}
