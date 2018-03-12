package com.xiuye.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MulticastClient {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(9999);
			DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
			log("waiting for broadcast");
			ds.receive(dp);
			ds.close();
			log("received msg: "+new String(dp.getData()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
