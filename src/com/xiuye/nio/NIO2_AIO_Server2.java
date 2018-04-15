package com.xiuye.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.ExecutionException;

public class NIO2_AIO_Server2 {

	private AsynchronousServerSocketChannel serverChannel;

	class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void>{

		private AsynchronousServerSocketChannel serverChannel;
		private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
		private CharBuffer charBuffer;
		private CharsetDecoder decoder = Charset.defaultCharset().newDecoder();

		public ServerCompletionHandler(AsynchronousServerSocketChannel serverChannel) {
			this.serverChannel = serverChannel;
		}


		@Override
		public void completed(AsynchronousSocketChannel result, Void attachment) {

			//立即接收下一个请求,不停顿
			serverChannel.accept(null,this);
			try {
				while(result.read(buffer).get()!=-1){
					buffer.flip();
					charBuffer = decoder.decode(buffer);
					String request = charBuffer.toString().trim();
					System.out.println("Client Request MSG: "+request);
					ByteBuffer outBuffer = ByteBuffer.wrap("Request Received!".getBytes());
					result.write(outBuffer).get();
					if(buffer.hasRemaining()){
						buffer.compact();
					}
					else{
						buffer.clear();
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			} catch (CharacterCodingException e) {
				e.printStackTrace();
			}
			finally{
				try {
					result.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		public void failed(Throwable exc, Void attachment) {
			//立即接收下一个请求,不停顿
			serverChannel.accept(null,this);
			throw new RuntimeException("connection failed!");
		}

	}

	public void init() throws IOException{
		this.serverChannel = AsynchronousServerSocketChannel.open();
		if(serverChannel.isOpen()){
			serverChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4*1024);
			serverChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
			serverChannel.bind(new InetSocketAddress(8080));
		}else{
			throw new RuntimeException("Channel not opened!");
		}
	}

	public void start() throws InterruptedException{
		System.out.println("Wait for Client ...");
		this.serverChannel.accept(null,new ServerCompletionHandler(serverChannel));
		while(true){
			Thread.sleep(5000);
		}
	}


	public static void main(String[] args) throws IOException, InterruptedException{

		NIO2_AIO_Server2 server = new NIO2_AIO_Server2();

		server.init();
		server.start();

	}

}
