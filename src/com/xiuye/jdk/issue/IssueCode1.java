package com.xiuye.jdk.issue;

public class IssueCode1 {

	public static void main(String[] args) {

		System.out.println(exponent(2.0,3));

	}

	public static double exponent(double x, int power) {
		if (power == 2) {
			return x * x;
		}
		return Math.pow(x, power);
	}

}
