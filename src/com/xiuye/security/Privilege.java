package com.xiuye.security;


public class Privilege {

	public static void main(String[] args) {

		System.setProperty("java.security.policy", "security.policy");
		System.setSecurityManager(new SecurityManager());
//		PrivilegedAction<String> readPropertiesAction = ()->{
//
//			log(System.getProperty("java.security.manager"));
//
//			return "OK";
//		};
//		log("Read Properties ? : "+AccessController.doPrivileged(readPropertiesAction));

		PrivilegeUtil.doPrivileged();
		log("===================================");
		PrivilegeUtil.printlnProperties("called by Main");

		log("===================================");
		Class<Privilege> clazz = Privilege.class;
		log("ProtectDomain := "+ clazz.getProtectionDomain());

	}

	private static<T> void log(T t){
		System.out.println(t);
	}

}
