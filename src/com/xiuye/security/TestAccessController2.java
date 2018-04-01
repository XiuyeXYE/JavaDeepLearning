package com.xiuye.security;

import java.io.FilePermission;
import java.security.AccessController;
import java.security.PrivilegedAction;

import sun.security.util.SecurityConstants;

public class TestAccessController2 {

	public static void main(String[] args) {

		//注意先后代码顺序
		System.setProperty("java.security.policy", "security.policy");
		System.setSecurityManager(new SecurityManager());

		log(System.getProperty("java.security.policy"));

		PrivilegedAction<Boolean> pa = ()->{
			FilePermission fp = new FilePermission("com",SecurityConstants.FILE_READ_ACTION);
			AccessController.checkPermission(fp);
			return true;
		};
		log(AccessController.doPrivileged(pa));

	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
