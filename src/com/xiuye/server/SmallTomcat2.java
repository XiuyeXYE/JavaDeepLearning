package com.xiuye.server;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SmallTomcat2 {

	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		try {
			tomcat.addWebapp("/sample", "F:\\Servers\\apache-tomcat-7.0.69\\webapps\\showcase-5.1");
			tomcat.init();
			tomcat.start();
			tomcat.getServer().await();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}

}
