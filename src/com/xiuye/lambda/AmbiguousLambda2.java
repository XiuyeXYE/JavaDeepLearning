package com.xiuye.lambda;

@FunctionalInterface
interface Fn {
	void f();
}

@FunctionalInterface
interface FnRet<T> {
	T f();
}

public class AmbiguousLambda2 {

	//it's OK();
	void AmbiguousLambda2(){
		log("Ordinay function");
	}

	public static void f(Fn f) {
		f.f();
	}

	public static <T> void f(FnRet<T> fr) {
		fr.f();
	}

	static <T> void log(T t) {
		System.out.println(t);
	}

	public static void main(String[] args) {
		AmbiguousLambda2 al = new AmbiguousLambda2();
		al.AmbiguousLambda2();
		f(()->log("not returned"));
		f(()->{log("returned");return 100;});
	}

}
