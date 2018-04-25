package com.xiuye.jdk.issue;

public class TestVirtual {

	public static void main(String[] args) {
		Father fr = new Father();
		fr.f();
		Father f = new Son();
		f.f();
		Son s = new Son();
		s.f();
	}

}

class Father{
	private String msg = "Father";
	public void f(){
		System.out.println("Msg: "+msg);
		this.g();
	}
	protected void g(){
		System.out.println("Father");
	}
}

class Son extends Father{
	private String msg = "Son";
	@Override
	protected void g() {
		System.out.println("Son");
	}
}
