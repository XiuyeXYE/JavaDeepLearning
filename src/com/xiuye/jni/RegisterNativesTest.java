package com.xiuye.jni;

public class RegisterNativesTest {

	private static native void registerNatives();
	static{
		registerNatives();
	}

	public static void main(String[] args) {

	}

}
