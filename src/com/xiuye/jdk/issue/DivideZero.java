package com.xiuye.jdk.issue;

public class DivideZero {

	public static void main(String []args){



		System.out.println(0.0000_0000_0000_01 == 0.0000_0000_0000_1);//false
		System.out.println(0x1p-1);
		System.out.println(0x1p-2);
		System.out.println(0x1p-3);
		System.out.println(0x1p1);
		System.out.println(0x1p2);
		System.out.println(0x2p1);//p==2
		System.out.println(0x2p2);//p==2
		System.out.println(1e1);
		System.out.println(1e-1);//e==10
		System.out.println(1_1_1_1);//1111
		System.out.println(1____1);//11
		System.out.println(0B00010);//2
		System.out.println(00010D);//10
		System.out.println(00010);//8

		System.out.println(-1.0/0);//Infinity 无限 负无穷
		System.out.println(1.0/0);//Infinity 无限 正无穷
		System.out.println(0.0/0);//NaN 未定义
		System.out.println(Float.NaN);
		System.out.println(Double.NaN);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(1/0);
	}

}
