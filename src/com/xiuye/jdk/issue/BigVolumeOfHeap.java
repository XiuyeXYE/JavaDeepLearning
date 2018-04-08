package com.xiuye.jdk.issue;

public class BigVolumeOfHeap {

	public static void main(String[] args) {
		int []a = new int[10];
		for(int i=0;i<a.length;i++){
			a[i] = 100;
		}
		System.out.println(a);
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}

}
