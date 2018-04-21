package com.lac.hackerrank;

import java.util.Scanner;

public class NonDivisibleSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*for each pair of dividers of k, for each nember in a,
		 * a[i]%k will result in these dividers, if the two number
		 *  of the paired divider are not equal, take the max count
		 * of each divider pair.Otherwise one one number can be added
		 * to the final result. For those members in a[] that results 
		 * in a[i]%k ==0, only one member can be added.
		 * 
		 */		
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}
		int counts[] = new int[k];
		for(int i=0;i<a.length;i++){
			counts[a[i]%k]++;
		}
		int count = Math.min(counts[0], 1);
		for(int i=1;i<k/2+1;i++){
			if(i!=k-i){
				count += Math.max(counts[i], counts[k-i]);
			}
		}
		if(k%2==0)
			count += 1;
		System.out.println(count);
	}

}
