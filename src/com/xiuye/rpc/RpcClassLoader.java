package com.xiuye.rpc;

public class RpcClassLoader extends ClassLoader {

	public Class<?> loadClass(byte []classData) throws ClassNotFoundException {
		return this.defineClass(null,classData, 0, classData.length,null);
	}

}
