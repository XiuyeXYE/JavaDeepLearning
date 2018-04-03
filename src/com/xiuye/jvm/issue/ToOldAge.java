package com.xiuye.jvm.issue;

public class ToOldAge {
	private static final int _1MB = 1024*1024;
	public static void main(String[] args) {
		byte [] a1;
		a1 = new byte[4*_1MB];
	}

}
