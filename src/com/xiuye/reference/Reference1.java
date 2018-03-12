package com.xiuye.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference1 {

	static class Bean{
		public Bean() {
			System.out.println("Bean Creation");
		}

		@Override
		public String toString() {

			return "I am Bean!";
		}
		@Override
		protected void finalize() throws Throwable {
			System.out.println("bean finally!");
		}
	}

	static class SubBean extends Bean{

		public SubBean() {
			System.out.println("SubBean Creation");
		}


	}

	public static void main(String[] args) {

		SoftReference<Bean> ref = new SoftReference<Bean>(new Bean());
		System.out.println(ref);
		System.gc();

		System.out.println(ref.get());
		WeakReference<Bean> ref2 = new WeakReference<Bean>(new Bean());
		System.out.println(ref2);
		System.out.println(ref2.get());

		System.gc();
		System.runFinalization();

		System.out.println(ref2.get());

		new SubBean();


	}

}
