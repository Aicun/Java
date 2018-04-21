package com.lac.codility;

import java.util.ArrayList;
import java.util.List;


public class CodilityEuclidean {

	public static void main(String[] args) {
		//int n = 10, m = 5;
		//int counts = ChocolatesByNumbers2(n,m);
		int a[] ={15,10,3};
		int b[] ={75,30,5};
		int counts = countcpd(a, b);
		
		int i = check(30,10);
		
		System.out.println(i);
	}
	
	public static int ChocolatesByNumbers(int n,int m){
		List<Integer> list =new ArrayList<Integer>();
		int chocolateIndex = 0;
		while(true){
			chocolateIndex = chocolateIndex % n ;
			if(!list.contains(chocolateIndex)){
				list.add(chocolateIndex);
			}else{
				break;
			}
			chocolateIndex = chocolateIndex+m;
		}
		return list.size();
	}
	
	public static int ChocolatesByNumbers2(int n,int m){
		int gcd = greatestCommonDivisor(n,m);
		int count = n/gcd;
		return count;
	}
	
	public static int greatestCommonDivisor(int a, int b){
		int result = 0;
		if(a % b == 0){
			result = b;
		}else{
			result = greatestCommonDivisor(b,a%b);
		}
		return result;
	}
	
	public static int removeCommonPrimeDivisors(int x,int y){
		while(x!=1){
			int gcd = greatestCommonDivisor(x,y);
			if(gcd == 1){
				break;
			}
			x /=gcd;
		}
		return x;
	}
	
	public static boolean hasSamePrimeDivisors(int x, int y){
		int gcd = greatestCommonDivisor(x,y);
		x = removeCommonPrimeDivisors(x,gcd);
		if(x!=1)
			 return false;
		y = removeCommonPrimeDivisors(y,gcd);
		return y == 1;
	}
	
	public static int countcpd(int a[], int b[]){
		int count = 0;
		for(int i=0;i<a.length;i++){
			if(hasSamePrimeDivisors(a[i],b[i]))
				count ++;
		}
		return count;
	}
	
	public static int check(int a, int gcd_ab)
	 {
	     //check if all the prime divisors of 'a' can be found
	     //in the prime divisors of gcd(a,b).
	     int rest = a / gcd_ab;
	     //if gcd(a, b) % rest == 0, that means all the prime divisors 
	     //of 'rest' is included in the prime divisors of gcd(a,b).
	     while (gcd_ab % rest != 0){
	         int gcd_tmp = greatestCommonDivisor(gcd_ab, rest);
	         //if gcd(a,b) have 1 as the gcd with rest larger,
	         //that means 'a / gcd(a,b)' contains some prime divisor that is not
	         //found in the prime divisors of gcd(a,b).
	         if (gcd_tmp == 1){
	             return 0;
	         }
	         rest /= gcd_tmp;
	     }
	     return 1;
	 }
}
