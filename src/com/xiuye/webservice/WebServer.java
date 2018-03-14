package com.xiuye.webservice;

import javax.xml.ws.Endpoint;

import com.xiuye.util.LogUtil;

public class WebServer {

	public static void main(String[] args) {
		LogUtil.log("Beginning ...");
		Endpoint.publish("http://localhost:8888/service/demoService",new DemoService());
		LogUtil.log("publish successfully!");
	}

}
