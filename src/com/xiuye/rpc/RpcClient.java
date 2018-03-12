package com.xiuye.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RpcClient {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost",8888);
			log("client calling server...");
			DemoService ds = (DemoService) new ObjectInputStream(s.getInputStream()).readObject();
			log(ds);
			log(ds.service("Hello,World!"));
			s.shutdownInput();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	private static<T> void log(T t){
		System.out.println(t);
	}
}
