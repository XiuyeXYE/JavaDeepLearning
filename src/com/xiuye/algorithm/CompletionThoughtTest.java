package com.xiuye.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletionThoughtTest {

	public static void main(String[] args) throws InterruptedException {

		Worker<String> worker = new Programmer();
		worker.doNext(new Programming(worker));

//		while(true){
//			Thread.sleep(500);
//		}

	}

}

class AtomIncrement{
	private static volatile int i = 0;
	public static synchronized void increment(){
		System.out.println(i++);
	}

}

class AtomThreadPool{

	//static 对应bytecode <cinit> JVM保证线程安全
	//什么懒加载单例模式,其实就是因为这个原因,线程安全.
	private static ExecutorService es = Executors.newFixedThreadPool(10);
	public static synchronized void add(Runnable r){
		es.execute(r);
	}
}


/**
 * 完成任务接口
 * @author xiuye
 *
 * @param <T>
 */
interface CompletionMission<T>{
	void doWork(T t);
}
/**
 *
 * 执行任务的 执行者
 *
 * @author xiuye
 *
 * @param <T>
 */
abstract class Worker<T>{

	public abstract void doNext(CompletionMission<T> nextTask);

}

class Programming implements CompletionMission<String>{
	private Worker<String> worker;

	public Programming(Worker<String> worker) {
		this.worker = worker;
	}

	@Override
	public void doWork(String t) {
		//这一步立即执行过去,why?看方法源码
		//过后的代码都能执行了,就会有函数返回,"不会"出现
		//StackOverflowError!
		//函数之间的相互递归调用,如果没有在方法中设置终止条件,就会很容易栈overflow!
		//为什么呢?这跟函数的调用方式和实现有关,函数调用的时候,临时数据和全局保存在栈上.
		//调用结束才出栈,递归调用,每次调用的数据,都要保存,如此下去就栈空间总会有不够的时候.
		//一般函数基于栈和寄存器的实现的.具体可以看C语言函数对应的汇编代码实现.
		//Java方法对应的指令集和字节码反应其是基于栈实现的,对应于虚拟机的.
		//OS和物理机器函数(procedure)实现的方式可能不同.
		this.worker.doNext(this);//这种间接地递归调用,"不阻塞"的调用,自动实现了循环,可以无止境执行下去.不需要while(true)了!
		AtomIncrement.increment();
		System.out.println("正在干什么: " + t);
		System.out.println("汉语:任务完成了.");

	}


}



class Programmer extends Worker<String>{

	@Override
	public void doNext(CompletionMission<String> nextTask) {
		//为什么要这么做?
		//以下直接执行过去,不会阻塞,不管run执行不执行,相当于异步.
		//把任务交给线程池管理,不会使线程超标(超过OS最大线程数),而出现异常.
		//有种回调函数(不一定马上执行的回调)的感觉啊.
		AtomThreadPool.add(()->nextTask.doWork("编程!"));

	}

}


