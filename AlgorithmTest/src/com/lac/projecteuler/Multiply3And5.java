package com.lac.projecteuler;

import java.util.HashSet;
import java.util.Set;

public class Multiply3And5 {

	public static void main(String args[]) {
		//System.out.println(processResult());
		
		double result = getResult(3, 999) + getResult(5, 999) - getResult(15, 999);
		System.out.println(result);
	}
	
	static long processResult() {
		int max1 = 999/3;
		int max2 = 999/5;
		Set<Integer> numbers = new HashSet<>();
		
		for(int i=1;i<=max1;i++) {
			numbers.add(i * 3);
		}
		
		for(int j=1;j<=max2;j++) {
			numbers.add(j * 5);
		}
		
		long sum = 0;
		for (Integer integer : numbers) {
			System.out.print(integer + " ");
			sum += integer;
		}
		
		return sum;
	}
	
	
	static double getResult(int start, int end) {
		int count = end/start;
		return start * (1 + count) * count / 2;
	}
}
