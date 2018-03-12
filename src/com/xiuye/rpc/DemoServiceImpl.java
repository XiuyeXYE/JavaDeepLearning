package com.xiuye.rpc;

public class DemoServiceImpl implements DemoService {

	/**
	 *
	 */
	private static final long serialVersionUID = 5512223035472673232L;

	@Override
	public String service(String str) {

		System.out.println("DemoServiceImpl Parameter: "+str);

		return "Return "+str+" OK";
	}

}
