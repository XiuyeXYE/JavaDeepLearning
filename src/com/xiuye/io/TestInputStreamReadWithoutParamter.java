package com.xiuye.io;

import java.io.IOException;

public class TestInputStreamReadWithoutParamter {

	public static void main(String[] args) {

		byte [] data = new byte[10];
		try {
			//把数据放到从data[3]开始的后续字节中
			System.in.read(data, 6, 4);

			for(int i=0;i<10;i++){
				System.out.println(data[i] + ""+(char)data[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
