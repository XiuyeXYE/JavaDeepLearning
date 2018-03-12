package com.xiuye.jndi.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Test {

	private static<T> void log(T t){
		System.out.println(t);
	}

	public static void main(String[] args) throws NamingException {

		Properties p1 = new Properties();
		p1.put(Context.INITIAL_CONTEXT_FACTORY, "com.xiuye.jndi.test.TestInitialContextFactory");
		p1.put("CONTEXT","TEST");

		log("Create a InitialContext 1");
		Context ctx1 = new InitialContext(p1);

		log("Look up a URL1");
		Object url1_val = ctx1.lookup("URL1");
		log(url1_val);
		log("Look up a URL2");
		Object url2_val = ctx1.lookup("URL2");
		log(url2_val);
		log("Look up a URL3");
		Object url3_val = ctx1.lookup("URL3");
		log(url3_val);
		log("Look up a URL4(not exist)");
		Object url4_val = ctx1.lookup("URL4");
		log(url4_val);


		log("=========================================");

		Properties p2 = new Properties();
		p2.put(Context.INITIAL_CONTEXT_FACTORY, "com.xiuye.jndi.test.TestInitialContextFactory");
		p2.put("CONTEXT","DEBUG");

		log("Create a InitialContext 2");
		Context ctx2 = new InitialContext(p1);

		log("Look up a URL1");
		url1_val = ctx2.lookup("URL1");
		log(url1_val);
		log("Look up a URL2");
		url2_val = ctx2.lookup("URL2");
		log(url2_val);
		log("Look up a URL3");
		url3_val = ctx2.lookup("URL3");
		log(url3_val);
		log("Look up a URL4(not exist)");
		url4_val = ctx2.lookup("URL4");
		log(url4_val);

	}

}
