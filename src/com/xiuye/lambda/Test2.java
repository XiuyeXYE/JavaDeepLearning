package com.xiuye.lambda;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {

		List<String> l = new ArrayList<String>();
		l.add("7");
		l.add("1");
		l.add("3");
		l.add("2");
		l.add("5");
		System.out.println(l);
		//The both are correct!
		//l.sort((a,b)-> {return a.compareToIgnoreCase(b);});
		//l.sort((String a,String b)-> {return a.compareToIgnoreCase(b);});
		l.sort((a,b)-> a.compareToIgnoreCase(b));
		l.forEach((e)->System.out.println(e));
		System.out.println(l);

	}

}
