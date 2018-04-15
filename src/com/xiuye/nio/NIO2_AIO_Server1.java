package com.xiuye.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class NIO2_AIO_Server1 {

	private ExecutorService taskExecutor;
	private AsynchronousServerSocketChannel serverChannel;
	class Worker implements Callable<String>{

		private CharBuffer charBuffer;
		private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
		private AsynchronousSocketChannel channel;
		public Worker(AsynchronousSocketChannel channel) {
			this.channel = channel;
		}

		@Override
		public String call() throws Exception {

			final ByteBuffer buffer = ByteBuffer.allocate(1024);
			while(channel.read(buffer).get() != -1){
				buffer.flip();
				charBuffer = decoder.decode(buffer);
				String request = charBuffer.toString().trim();
				System.out.println("Client Request MSG: "+request);
				ByteBuffer outBuffer = ByteBuffer.wrap("Request Received!".getBytes());
				channel.write(outBuffer).get();
				if(buffer.hasRemaining()){
					buffer.compact();
				}
				else{
					buffer.clear();
				}
			}

			channel.close();
			return "OK";
		}

	}

	public void init() throws IOException{
		taskExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
		serverChannel = AsynchronousServerSocketChannel.open();
		if(serverChannel.isOpen()){
			serverChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4*1024);
			serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			serverChannel.bind(new InetSocketAddress(8080));
		}
		else{
			throw new RuntimeException("Channel not opened!");
		}
	}

	public void start(){
		System.out.println("Wait for client ...");
		while(true){//虽然是异步还是有循环啊
			Future<AsynchronousSocketChannel> future = serverChannel.accept();

			AsynchronousSocketChannel channel;
			try {
				channel = future.get();
				taskExecutor.submit(new Worker(channel));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				System.err.println("Server closed!");
				while(!taskExecutor.isTerminated()){}
				break;
			}



		}
	}

	public static void main(String[] args) throws IOException {

		NIO2_AIO_Server1 server = new NIO2_AIO_Server1();
		server.init();
		server.start();

	}

}
