package com.xiuye.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class SmallTomcat1 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		HttpServlet servlet = new HttpServlet() {
			/**
			 *
			 */
			private static final long serialVersionUID = 5639776356541557752L;

			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				resp.getWriter().write("<h1 style='color:red'>Hello,I am embeded tomcat!</h1>\r\n");
			}
		};

		Context ctx = tomcat.addContext("/hello", null);
		Tomcat.addServlet(ctx, "/servlet", servlet);
		ctx.addServletMapping("/servlet", "/servlet");
		try {
			tomcat.init();
			tomcat.start();
			tomcat.getServer().await();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}

	}

}
