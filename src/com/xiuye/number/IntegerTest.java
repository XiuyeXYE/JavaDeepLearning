package com.xiuye.number;

public class IntegerTest {

	public static void main(String[] args) {

		System.out.println(new Integer(1) == new Integer(1));
		System.out.println(1 == new Integer(1));
		System.out.println(150 == new Integer(150));
		System.out.println(15000 == new Integer(15000));
		System.out.println(15000 == new Integer(15000));
	}

}
