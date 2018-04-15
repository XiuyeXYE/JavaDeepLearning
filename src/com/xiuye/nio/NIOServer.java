package com.xiuye.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

	private Selector selector;
	public void init() throws IOException{
		this.selector = Selector.open();
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		ServerSocket ss = channel.socket();
		ss.bind(new InetSocketAddress(8080));
		//server 注册 event accept
		channel.register(selector, SelectionKey.OP_ACCEPT);
	}

	public void start() throws IOException{
		while(true){
			this.selector.select();//blocking 阻塞
			Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();
				it.remove();//删除防止下次重复处理
				if(key.isAcceptable()){
					accept(key);
				}
				else if(key.isReadable()){
					read(key);
				}

			}
		}
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer);
		String request = new String(buffer.array()).trim();
		System.out.println("Client Request MSG: "+request);
		ByteBuffer outBuffer = ByteBuffer.wrap("Request had received!".getBytes());
		channel.write(outBuffer);
		//channel.close();
	}

	private void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverChnnel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverChnnel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);
	}


	public static void main(String[] args) throws IOException {

		/**
		 * Selector and channel are independent
		 * Selector registers all kinds of channels
		 * Selector Event is SelectionKey
		 * All in all,
		 * Selector selects Channel
		 * Channel manipulates Buffer
		 * Selector registers next Channel (maybe different from previous one)
		 */
		NIOServer server = new NIOServer();
		server.init();
		server.start();

	}

}
