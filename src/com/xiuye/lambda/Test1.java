package com.xiuye.lambda;

import java.util.function.Function;

public class Test1 {

	interface Num{
		int get();
		default String description(){
			return "返回数字";
		}
		default String say(){
			return "返回数字";
		}
		static String sayHello(){
			return "Hello!";
		}
	}

	interface Func{
		static String description(){
			return "传参函数,并调用";
		}
		String call();

	}

	interface F<T>{//Class::instanceMethod
		void f(T t);
	}

	interface Instance<T>{
		T newInstance();
	}

	public static void call(Func f){
		System.out.println(f.call());
	}

	static<T> T newIns(Instance<T> i){
		return i.newInstance();
	}

	public Test1(){
		System.out.println("我被构造了!");
	}

	Void printThis(){
		call(()->{System.out.println(this);return "OK";});
		return null;
	}

	public static void main(String[] args) {
		Num x = ()->5;
		System.out.println(x.description());
		System.out.println(x.say());
		System.out.println(Num.sayHello());
		System.out.println(x.get());
		call(Num::sayHello);
		call(x::description);
		call(Func::description);
		//Test1 t = new Test1();
		Test1 t = newIns(Test1::new);
		t.printThis();

		Function<Test1,Void> f = Test1::printThis;
		f.apply(t);

		F<Test1> ff = Test1::printThis;
		ff.f(t);

	}

}
