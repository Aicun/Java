package com.lac.hackerrank;

import java.util.Scanner;

public class Squares {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 0; i < t; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			double sqrta = Math.sqrt(a);
			double sqrtb = Math.sqrt(b);
			double ceila = Math.ceil(sqrta);
			double floorb = Math.floor(sqrtb);
			
			double gap = floorb-ceila+1;
			
			System.out.println(new Double(gap).intValue());
		}
	}

}
