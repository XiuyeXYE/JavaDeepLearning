package com.xiuye.bytecode;

public class HotSwapClassLoader extends ClassLoader {

	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
	}

	public Class<?> loadByte(byte[] data){
		return this.defineClass(null, data, 0, data.length);
	}

}
