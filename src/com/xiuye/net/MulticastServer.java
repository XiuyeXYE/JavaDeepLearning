package com.xiuye.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MulticastServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket();
			log("server open");
			String msg = "Hello World";
			//单播：单台主机与单台主机之间的通信
			//广播：当台主机与网络中的所有主机通信
			//多播：当台主机与选定的一组主机的通信;组播IP地址就是D类IP地址，即224.0.0.0至239.255.255.255之间的IP地址
			//127.0.0.1 -- OK
			//225.255.255.255 -- OK broadcast
			DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.length(),InetAddress.getByName("225.255.255.255"),9999);
			ds.send(dp);
			log("server send data!");
			ds.close();
			log("server over");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static<T> void log(T t){
		System.out.println(t);
	}
}
