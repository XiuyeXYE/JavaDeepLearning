package com.xiuye.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.xiuye.util.LogUtil;

@WebService
public class DemoService {


	public void serviceOne(){
		LogUtil.log("Service One");
	}
	@WebMethod(exclude=true)
	public void serviceTwo(){
		LogUtil.log("Service Two");
	}

}
