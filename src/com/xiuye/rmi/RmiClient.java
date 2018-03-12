package com.xiuye.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RmiClient {

	public static void main(String[] args) {
		try {
			DemoService ds = (DemoService) Naming.lookup("rmi://localhost:9999/ds");
			log(ds.hello("hello"));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
