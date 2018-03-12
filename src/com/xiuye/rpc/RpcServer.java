package com.xiuye.rpc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcServer {

	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(8888);
			log("server begin...");
			Socket s = ss.accept();
			log("client request coming!");
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(new DemoServiceImpl());
			oos.flush();
			//此处必须写,否则,java.net.SocketException: Connection reset
			s.shutdownOutput();
//			s.close();//也可以
			log("send data to client over!");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
