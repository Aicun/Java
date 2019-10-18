package com.lac.projecteuler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrimeNumber {
	
	public static void main(String[] args) {
		long target = 600851475143l;
		
		System.out.println(GetLargestPrime(target));
	}
	
	static boolean isPrime(long number) {
		if(number <=1) return false;
		if(number <=3) return true;
		if(number % 2 == 0 || number % 3 == 0) return false;
		
		int i = 5;
		while(i*i <= number) {
			if(number % i == 0 || number % (i + 2) == 0)
				return false;
			i += 6;
		}
		return true;
	}
	
	static Set<Long> calcFactors(long number) {
		Set<Long> factors = new HashSet<Long>();
		
		for( long i = 2; i * i <=number; i++) {
			if(number % i == 0) {
				factors.add(i);
				factors.add(number / i);
			}
		}
		return factors;
	}
	
	static long GetLargestPrime(long number) {
		
		long primeFactor = 0;
		
		for( long i = 3; i * i <=number; i+=2) {
			if(number % i == 0) {
				
				long factor = number / i;
				
				if(isPrime(i)) {
					if( i > primeFactor)
						primeFactor = i;
				}
				
				if(isPrime(factor)) {
					if(factor > primeFactor)
						primeFactor = factor;
				}
			}
		}
		return primeFactor;
	}
}