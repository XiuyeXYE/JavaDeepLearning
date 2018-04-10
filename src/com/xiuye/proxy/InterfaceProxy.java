package com.xiuye.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceProxy {


	public static void main(String []args){

		IHello h = new DynamicProxy<IHello>().bind(new Hello());
//		h.f();
		System.out.println(h);

	}

	interface IHello {
		void f();
	}

	static class Hello implements IHello{

		@Override
		public void f() {
			System.out.println("Hello World!");
		}

	}

	static class DynamicProxy<T> implements InvocationHandler{

		private T obj;

		@SuppressWarnings("unchecked")
		public T bind(T t){
			this.obj = t;
			return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(),t.getClass().getInterfaces(),this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Welcome");
//			System.out.println(proxy);
			return method.invoke(obj, args);
		}

	}

}
