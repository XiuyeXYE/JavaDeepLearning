package com.xiuye.extension;

import java.lang.reflect.Field;

public class GetExtLibsPath {

	public static void main(String[] args) {

		System.out.println(System.getProperty("java.ext.dirs"));
		Field []fs = GetExtLibsPath.class.getDeclaredFields();
		for(Field f:fs){
			System.out.println(f);
		}


	}

	public void run(){
		System.out.println("running");
	}

}
