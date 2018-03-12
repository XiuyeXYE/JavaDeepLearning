package com.xiuye.jdk.issue;

import java.util.HashMap;
import java.util.Map;

public class IssueCode2 {

	static class Bean {
		private int i;
		private int j;
		private String str;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}

		@Override
		public String toString() {

			return i +","+ j+"," + str;
		}
		@Override
		protected void finalize() throws Throwable {
			System.out.println("finalize executed!");
		}

	}

	public static void main(String[] args) {

		Map<Integer, Bean> map = new HashMap<Integer, Bean>();
		Bean b = new Bean();//first reference
		b.setI(100);
		b.setJ(88);
		b.setStr("C++");
		System.out.println(b);
		map.put(1, b);//second reference
		System.out.println(b);
		System.out.println(map);
		b.setI(99);
		b.setJ(11);
		b.setStr("Java");
		System.out.println("re:" + b);
		System.out.println("re:" + map);

	}

}
