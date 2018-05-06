package com.xiuye.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class RmiServer {

	public static void main(String[] args) {
		try {

			DemoService ds = new DemoServiceImpl();
			LocateRegistry.createRegistry(9999);
			Naming.rebind("rmi://localhost:9999/ds", ds); 
			log("binded ip with obj");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
