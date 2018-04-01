package com.xiuye.jdk.issue;

import java.util.Random;

public class TestI {

	static class A {
		public static void f(){
			System.out.println("A");
		}
	}

	static class B extends A {
		public static void f(){
			System.out.println("B");
		}
	}

	public static void main(String[] args) {
		A a = new B();
		a.f();
		B b = (B) a;
		b.f();
	}
	public static void main1(String[] args) {
		// int i=10;
		// i = --i;
		// System.out.println(i);
		Random r = new Random();

		int[] arr = new int[] { 0,4, 0, 7, 0, 2,1,0,2,0 };
		arr = new int[10000];
		for(int i=0;i<arr.length;i++){
			arr[i] = r.nextInt(10);
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
//		int k = 0;
//		for (int i = 0; i < arr.length-k; i++) {
//
//			if (arr[i] == 0) {
//				for (int j = i; j < arr.length-k-1; j++) {
//					int tmp = arr[j+1];
//					arr[j+1] = arr[j];
//					arr[j] = tmp;
//				}
//				k++;
//				i=0;
//			}
//		}
		f1(arr);
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();

	}

	public  static void f(int []nums){
		int size = nums.length;
        int startIndex = 0;
        int endIndex = 0;
        int currentNum;
        int i= 0;
        while(i < size){
            currentNum = nums[i];
            if (currentNum == 0) {
                startIndex = i;
                endIndex = i;
                break;
            }
            ++i;
        }
        if (nums[endIndex] != 0)
            return;

        ++i;
        while (i < size) {
            currentNum = nums[i];
            if (currentNum == 0){
                    endIndex = i;
            } else {
                nums[startIndex] = currentNum;
                nums[i] = 0;
                ++startIndex;
                ++endIndex;
            }
            ++i;
        }
    }

	public static void f1(int []nums){
		int i;
		int j=0;
		for(i=0;i<nums.length;i++){
			if(nums[i] == 0){
				continue;
			}
			else{
				nums[j] = nums[i];
				j++;
			}
		}
		for(i=j;i<nums.length;i++){
			nums[i] = 0;
		}

	}


}
