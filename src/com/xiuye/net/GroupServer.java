package com.xiuye.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupServer {

	public static void main(String[] args) {
		try {
			MulticastSocket ms = new MulticastSocket();
			ms.setTimeToLive(55);
			String h = "Hello World!";
			DatagramPacket dp = new DatagramPacket(h.getBytes(), h.length(),InetAddress.getByName("224.0.0.0"),9999);
			ms.send(dp);
			ms.close();
			log("server over");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static<T> void log(T t){
		System.out.println(t);
	}

}
