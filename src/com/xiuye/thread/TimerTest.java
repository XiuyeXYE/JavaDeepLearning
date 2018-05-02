package com.xiuye.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) {

		Timer t = new Timer("Test");
		t.schedule(new TimerTask() {

			@Override
			public void run() {

				System.out.println("called");
			}
		}, 10);
//		t.cancel();


	}

}
