package com.xiuye.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DemoServiceImpl extends UnicastRemoteObject implements DemoService {

	/**
	 *
	 */
	private static final long serialVersionUID = -8127961337458650695L;

	protected DemoServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public String hello(String param) throws RemoteException {
		System.out.println("Server Called!");
		return "OK";
	}

}
