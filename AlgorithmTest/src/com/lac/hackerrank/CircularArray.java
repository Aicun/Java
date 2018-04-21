package com.lac.hackerrank;

import java.util.Scanner;

public class CircularArray {

	public static void run() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int q = in.nextInt();
		int[] a = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		int t = k % n;

		for (int a0 = 0; a0 < q; a0++) {
			int m = in.nextInt();
			if (m - t >= 0)
				System.out.println(a[m - t]);
			else
				System.out.println(a[m-t+n]);
		}
	}

	public static void main(String[] args) {
		run();
	}
}
