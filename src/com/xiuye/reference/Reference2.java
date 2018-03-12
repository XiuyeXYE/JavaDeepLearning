package com.xiuye.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Reference2 {

	static class A {
		@Override
		public String toString() {
			return "A::::::" + super.toString();
		}
	}

	/**
	 *
	 * StrongReference => T t = new T();
	 *当发生GC的时候,无论什么时候都不会回收.
	 * SoftReference => SoftReference<T> sf
	 * 						= new SoftReference<T>(new T(), ReferenceQueue)
	 * 当发生GC的时候,软引用内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，
	 * 就会回收这些对象的内存.
	 * WeakReference => WeakReference<T> wf
	 * 						= new WeakReference<T>(new T(), ReferenceQueue)
	 *当发生GC的时候,发现了具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存.
	 *PhantomReference => PhantomReference<T> pr = new PhantomReference<T>(new T(), ReferenceQueue);
	 *当发生GC的时候,虚引用对象加入到与之关联的引用队列.当JVM将虚引用插入到引用队列的时候，虚引用执行的对象内存还是存在的。
	 *
	 *每次gc后,soft,weak,phantom的引用的实例对象一般会在ReferenceQueue的队列里.
	 *
	 *创建的对象实例不管什么引用,只要实例对象有强引用,不会在gc后加入到ReferenceQueue里面.
	 *
	 *ReferenceQueue保存的是Reference及其子类的实例对象,不是new T()的对象.
	 *
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		A a = new A();
//		log("Created,a = "+a);
		ReferenceQueue<A> rq = new ReferenceQueue<>();
		WeakReference<A> wr = new WeakReference<A>(a, rq);
		log(wr.get());
		log(rq.poll());
//		log("Before GC,a = " + a);

		System.gc();
		Thread.sleep(3000);

		log(wr.get());
		log(rq.poll());//仍然为null,gc后没有把Reference的对象加入到ReferenceQueue中.
//		log("After GC,a = " + a);


		log("=====================================");

		rq = new ReferenceQueue<>();
		wr = new WeakReference<A>(new A(), rq);
		log(wr.get());
		log(rq.poll());

		System.gc();
		Thread.sleep(3000);

		log(wr.get());
		log(rq.poll());

		log("=====================================");

		a = new A();
//		log("Created,a = "+a);
		rq = new ReferenceQueue<>();
		PhantomReference<A> pr = new PhantomReference<A>(a, rq);
		log(pr.get());
		log(rq.poll());
//		log("Before GC,a = " + a);

		System.gc();
		Thread.sleep(3000);

		log(pr.get());
		log(rq.poll());//仍然为null,gc后没有把Reference的对象加入到ReferenceQueue中.
//		log("After GC,a = " + a);

		log("===========================");

		rq = new ReferenceQueue<>();
		//对象直接new实例传入,不要像上面的a是个强引用,gc后不会被回收的.
		//每次gc后,soft,weak,phantom的引用的实例对象一般会在ReferenceQueue的队列里.
		pr = new PhantomReference<A>(new A(), rq);
		log(pr.get());
		log(rq.poll());

		System.gc();
		Thread.sleep(3000);//暂停让对象实例到队列里.

		log(pr.get());

		log(rq.poll());

		/**
		 * 结论:
		 * 通过以上发现,这些所谓的引用,不过是对对象实例的一种包装,相当于实例对象的容器.
		 * 只要new的对象实例直接传入Reference的那几个子类中,就能发挥reference
		 * (对象引用)的作用,包括软引用,弱引用,虚(幽灵)引用.
		 * gc后,Reference的实例对象会放到ReferenceQueue里.
		 *
		 * 这些所谓的 软引用,弱引用,虚(幽灵)引用 是怎么实现的呢?也可以说为什么这么做.
		 * 1.包装类Reference.(作用相当于引用,如:A a = new A(),a就是Reference)
		 * 2.直接传入实例对象,即在构造方法里new A().
		 * 因为 A a = new A()是强引用,无论实例对象在哪个位置,只要强引用存在,
		 * 内存中的对象就不会被GC回收
		 * 所以Reference r = new Reference(new A(),quene);
		 * 而不是A a = new A(); Reference r = new Reference(a,quene);传入有强引用的实例a.
		 * 只有这样 软引用,弱引用,虚(幽灵)引用 才能发挥作用.
		 * 3.在Reference保存的实例对象,与外界没有任何关系(没有外部引用),做什么样操作,都可以自己选择.
		 * 如:在Reference的内部,将内部的对实例对象的引用设为null,那是不是意味着
		 * 对象就可以被回收呢,或者放到ReferenceQuene里,让它在内存中,外界是无法访问到的.
		 *
		 *
		 */
	}

	private static <T> void log(T t) {
		System.out.println(t);
	}

}
