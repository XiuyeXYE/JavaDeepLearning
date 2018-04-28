package com.xiuye.security;

import java.security.Permission;

public class IsBug {

	static class A{

	}

	public static void main(String[] args) {

		System.out.println("beginning...");

		System.setSecurityManager(new SecurityManager(){
			@Override
			public void checkPermission(Permission perm) {
				new A();
			}
		});

		System.out.println("end");

	}

}
