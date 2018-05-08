package com.xiuye.thread;

import java.util.ArrayList;
import java.util.List;

public class SyncIsFixedAsync {

	static volatile int i = 0;

	private List<Thread> ts = new ArrayList<>();//使线程保持全局有效果,因为局部变量出了作用域就消失了.

	public synchronized void test() {
		i++;
		System.out.println("Open 线程 : " + i);
		// 异步代码块
		{
			Thread t = new Thread(() -> {
				while (true) {
					System.out.println("我是线程: " + i + " 号!");
				}
			});
			t.start();
			ts.add(t);
		}
		System.out.println("End 线程 : " + i);
	}

	public static void main(String[] args) throws InterruptedException {

		SyncIsFixedAsync sifa = new SyncIsFixedAsync();

		new Thread(() -> {// 线程
			sifa.test();
		}).start();
		new Thread(() -> {// 线程
			sifa.test();
		}).start();

		while(true){
			Thread.sleep(5000);
			System.out.println("线程们的状态:");
			for(int i=0;i<sifa.ts.size();i++){
				System.out.println(i+" is alive? " + sifa.ts.get(i).isAlive());
			}
		}
	}

}
