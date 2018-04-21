package com.lac.hackerrank;

import java.util.Scanner;

public class SavePrisoner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int n = in.nextInt();
			int m = in.nextInt();
			int s = in.nextInt();
			int number = m % n;
			int prisoner = (s+number)%n-1;
			System.out.println(prisoner==0?n:prisoner);
		}
	}
}
