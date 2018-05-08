package com.xiuye.thread;

public class SyncIsCorrect {

	static volatile int i = 0;

	public synchronized void test() {
		System.out.println("Open 线程 : " + i);

		// 相加100次
		for (int j = 0; j < 10000; j++) {
			i++;
		}

		System.out.println("End 线程 : " + i);
	}

	public static void main(String[] args) throws InterruptedException {

		SyncIsCorrect sic = new SyncIsCorrect();

		Thread t1 = new Thread(() -> {// 线程
			sic.test();
		});
		t1.start();

		Thread t2 = new Thread(() -> {// 线程
			sic.test();
		});
		t2.start();

		t1.join();
		t2.join();
		System.out.println("结果: " + sic.i);

	}

}
