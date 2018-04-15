package com.xiuye.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

public class NIO2_AIO_Client1 {

	private AsynchronousSocketChannel channel;
	private CharBuffer charBuffer;
	private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
	private BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

	public void init() throws IOException, InterruptedException, ExecutionException{
		channel = AsynchronousSocketChannel.open();
		if(channel.isOpen()){
			channel.setOption(StandardSocketOptions.SO_RCVBUF, 128*1024);
			channel.setOption(StandardSocketOptions.SO_SNDBUF, 128*1024);
			channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
			Void connect = channel.connect(new InetSocketAddress("127.0.0.1",8080)).get();
			if(connect != null){
				throw new RuntimeException("connect failed!");
			}

		}
		else{
			throw new RuntimeException("Channel not opened!");
		}
	}

	public void start() throws IOException, InterruptedException, ExecutionException{
		System.out.println("Input Client Resqust MSG:");
		String request = clientInput.readLine();
		channel.write(ByteBuffer.wrap(request.getBytes()));
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while(channel.read(buffer).get() != -1){
			buffer.flip();
			charBuffer = decoder.decode(buffer);
			String response = charBuffer.toString().trim();
			System.out.println("Server replied MSG: "+response);
			if(buffer.hasRemaining()){
				buffer.compact();
			}
			else{
				buffer.clear();
			}
			request = clientInput.readLine();
			channel.write(ByteBuffer.wrap(request.getBytes())).get();
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		NIO2_AIO_Client1 client = new NIO2_AIO_Client1();
		client.init();
		client.start();
	}

}
