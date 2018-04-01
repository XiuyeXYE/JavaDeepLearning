package com.xiuye.security;

import java.io.FilePermission;
import java.net.NetPermission;
import java.security.AccessControlContext;
import java.security.AccessController;

public class TestAccessController {

	public static void main(String[] args) {

		AccessControlContext ctx = AccessController.getContext();
		if(ctx != null){
			System.out.println(ctx);
			FilePermission perm = new FilePermission("D:\\programming\\DevelopingProjects\\JavaAPILearning\\p1.jpg", "read");
//			ctx.checkPermission(perm);
//			System.out.println(System.getProperty("java.security.manager"));
//			System.setSecurityManager(null);
//			System.setProperty("java.security.manager");
			System.out.println(System.getSecurityManager());
			SecurityManager sm = System.getSecurityManager();
			sm.checkAccess(Thread.currentThread());
			Thread t = new Thread();
			sm.checkAccess(t);
			t.start();

			System.out.println();
			System.out.println(ctx);

		}

	}

}
