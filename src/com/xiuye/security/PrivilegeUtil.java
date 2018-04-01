package com.xiuye.security;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class PrivilegeUtil {

	public static void doPrivileged(){

		PrivilegedAction<String> readPropertiesAction = ()->{

			log("doPrivileged");
			printlnProperties("called by doPrivileged");

			return "OK";
		};
		log("Read Properties ? : "+AccessController.doPrivileged(readPropertiesAction));

	}

	public static void printlnProperties(String s){
		log("printlnProperties "+s);
		log(System.getSecurityManager());
	}

	private static<T> void log(T t) {
		System.out.println(t);
	}

}
