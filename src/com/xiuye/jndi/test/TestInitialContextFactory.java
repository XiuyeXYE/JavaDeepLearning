package com.xiuye.jndi.test;

import java.util.Hashtable;
import java.util.ServiceLoader;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class TestInitialContextFactory implements InitialContextFactory{


	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {

		String context = (String) environment.get("CONTEXT");
		Context ctx = null;
		switch (context.toLowerCase()) {
		case "test":
			ServiceLoader<Context> contextLoader = ServiceLoader.load(Context.class);
			ctx = contextLoader.iterator().next();
			ctx.rebind("URL1", "URL1 OK");
			ctx.rebind("URL2", "URL2 OK");
			ctx.rebind("URL3", 3);
			break;
		case "debug":
			break;
		case "release":
			break;
		default:
			break;
		}
		if(ctx == null){
			ctx = new TestContext();
			ctx.rebind("URL1", "URL1 OK");
			ctx.rebind("URL2", "URL2 OK");
			ctx.rebind("URL3", 3);
		}


		return ctx;
	}

}
