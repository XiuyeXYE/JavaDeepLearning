package com.xiuye.lambda;

import java.lang.management.ManagementFactory;
import java.util.List;

@FunctionalInterface
interface Fn1 {
	default void f() {
		System.out.println("Fn1 f");
	}

	void g();
}

@FunctionalInterface
interface Fn2 {
	void h();
}
@FunctionalInterface
interface Fn3 {
	boolean staticFn();
}

public class AmbiguousLambda {

	static void f(Fn1 f1) {
		f1.f();
		f1.g();
	}

	static void f(Fn2 f2) {
		f2.h();
	}

	static boolean checkReturn(Fn3 f3){
		return f3.staticFn();
	}

	public static void main(String[] args) {
		/**
		 * Ambiguous !!!!!
		 * Exception in thread "main" java.lang.Error: Unresolved compilation
		 * problem: The method f(Fn1) is ambiguous for the type AmbiguousLambda
		 */
//		AmbiguousLambda.f(() -> System.out.println("Who called!"));
		//Solve it
		Fn1 f1 = ()->System.out.println("Fn1 called!");
		Fn2 f2 = ()->System.out.println("Fn2 called!");
		AmbiguousLambda.f(f1);
		AmbiguousLambda.f(f2);
		System.out.println("static return := "+checkReturn(AmbiguousLambda::retBoolean));
		List<String> params = ManagementFactory.getRuntimeMXBean().getInputArguments();
		System.out.println("java "+params);
		System.out.println(System.currentTimeMillis());
	}

	static boolean retBoolean(){
		return true;
	}

}
