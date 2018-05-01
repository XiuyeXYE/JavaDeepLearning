package com.xiuye.classloader;

import com.sun.glass.utils.NativeLibLoader;

public class NativeLibraryTest {

	public static void main(String[] args) {

		NativeLibLoader.loadLibrary("msvcr100");
		System.out.println("OK");

	}

}
