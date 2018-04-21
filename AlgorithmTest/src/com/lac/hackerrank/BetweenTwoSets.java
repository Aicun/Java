package com.lac.hackerrank;

import java.util.Scanner;

public class BetweenTwoSets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculate();
	}

	public static void calculate() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		int[] b = new int[m];
		for (int b_i = 0; b_i < m; b_i++) {
			b[b_i] = in.nextInt();
		}

		int lcm = lcm(a);
		int gcd = gcd(b);
		int count = 0;
		for (int i = lcm, j = 2; i <= gcd; i = lcm * j, j++) {
			if (gcd % i == 0)
				count++;
		}
		System.out.println(count);
	}

	// greatest common divisor
	public static int gcd(int a, int b) {
		int result = 0;
		if (a % b == 0) {
			result = b;
		} else {
			result = gcd(b, a % b);
		}
		return result;
	}

	public static int gcd(int[] input) {
		int result = input[0];
		for (int i = 1; i < input.length; i++) {
			result = gcd(result, input[i]);
		}
		return result;
	}

	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public static int lcm(int[] input) {
		int result = input[0];
		for (int i = 1; i < input.length; i++) {
			result = lcm(result, input[i]);
		}
		return result;
	}

}
