package com.xiuye.security;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class TestSecurutyManager {

	public static<T> void log(T t){
		System.out.println(t);
	}

	public static void main(String[] args) {
		SecurityManager security = System.getSecurityManager();
		String s = AccessController.doPrivileged(new PrivilegedAction<String>() {

			@Override
			public String run() {
				log("TEST1");
				return "123";
			}
		});
		log("ret " + s);
		PrivilegedAction<String> f =()->"123";
		s = AccessController.doPrivileged(f);
		log("ret " + s);
		//ambiguous 歧义
//		s = AccessController.doPrivileged(()->"123");
//		log("ret " + s);

		if(security != null){
			log(security);
//			security.checkRead("p1.jpg");
			return;
		}


	}

}
