package com.xiuye.jdk.issue;

class f{
	static{
		System.out.println("f");
	}
	public static int value = 100;
}
class s extends f{
	static{
		System.out.println("s");
	}
}


public class TEST1 {

	public static void main(String []args){
//		System.out.println(s.value);
		s [] ss = new s[100];
	}

}
