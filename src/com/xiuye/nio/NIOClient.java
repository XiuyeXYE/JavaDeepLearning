package com.xiuye.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

	private Selector selector;

	private BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

	public void init() throws IOException{
		this.selector = Selector.open();
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("127.0.0.1", 8080));
		channel.register(selector, SelectionKey.OP_CONNECT);
	}

	public void start() throws IOException{
		while(true){
			this.selector.select();//blocking
			Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key = it.next();
				it.remove();//delete this key from "keys set" in case of repetition
				if(key.isConnectable()){
					connect(key);
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
		String response = new String(buffer.array()).trim();
		System.out.println("Server Replied MSG: "+response);
		String nextRequest = clientInput.readLine();
		ByteBuffer outBuffer = ByteBuffer.wrap(nextRequest.getBytes());
		channel.write(outBuffer);
//		channel.close();
	}

	private void connect(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel)key.channel();
		if(channel.isConnectionPending()){//is connecting ...?
			if(channel.finishConnect()){
				channel.configureBlocking(false);
				channel.register(selector, SelectionKey.OP_READ);
				String request = clientInput.readLine();
				channel.write(ByteBuffer.wrap(request.getBytes()));
			}
			else{
				key.cancel();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//channel operate buffer not byte[]
		//channel register into seletor
		//selectionkey deleted in hand
		NIOClient client = new NIOClient();
		client.init();
		client.start();
	}

}
