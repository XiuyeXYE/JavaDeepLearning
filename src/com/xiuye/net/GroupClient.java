package com.xiuye.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class GroupClient {

	public static void main(String[] args) {
		try {
			MulticastSocket ms = new MulticastSocket(9999);
			//224.0.0.0至239.255.255.255
			ms.joinGroup(InetAddress.getByName("224.0.0.0"));
			DatagramPacket dp = new DatagramPacket(new byte[1024], 1024);
			ms.receive(dp);
			log(new String(dp.getData()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static<T> void log(T t){
		System.out.println(t);
	}
}
