package com.xiuye.image;

import java.util.HashMap;
import java.util.Map;

public class MapOp1 {

	private static class D{
		private String t;
		public D(String s) {
			t = s;
		}
		@Override
		public String toString() {
			return t;
		}
	}

	public static void main(String[] args) {
		Map<String,D> map = new HashMap<>();
		D d1 = new D("123");
		map.put("123",d1);
		D d2 = new D("456");
		map.put("456",d2);

		System.out.println(map);

		d1 = new D("789");
		System.out.println(map);
	}

}
