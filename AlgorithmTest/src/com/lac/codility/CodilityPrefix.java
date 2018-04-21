package com.lac.codility;

public class CodilityPrefix {
	public static void main(String[] args) {
		 int a[] = {-3, -5, -8, -4, -10};
		int result = startPosition2(a);
		System.out.println(result);
	}
	
	public static int startPosition2(int[] a){
		int length = a.length;
		double avg = Integer.MAX_VALUE;
		int index = 0;
		for(int i=0;i<length-2;i++){
			float sumTwoAvg = (float)(a[i]+a[i+1])/2;
			if(sumTwoAvg<avg){
				avg = sumTwoAvg;
				index = i;
			}
			float sumThreeAvg = (float)(a[i]+a[i+1]+a[i+2])/3;
			if(sumThreeAvg<avg){
				avg = sumThreeAvg;
				index = i;
			}
		}
		
		float sumLastTwoAvg = (float)(a[length-2]+a[length-1])/2;
		if(sumLastTwoAvg<avg){
			avg = sumLastTwoAvg;
			index = length-2;
		}
		return index;
	}
	
	public static int startPosition(int[] a){
		int length = a.length;
		int presum[] = new int[length+1];
		int start = 0;
		for(int i=1;i<length+1;i++){
			presum[i] = presum[i-1]+a[i-1];
		}
		double avg = Integer.MAX_VALUE;
		for(int i=1;i<length;i++){
			for(int j=i+1;j<length+1;j++){
				double temp = (presum[j]-presum[i])/(j-i+1);
				if(temp<avg){
					avg = temp;
					start = i-1;
				}
			}
		}
		return start;
	}

	public static int[] dnaImpact2(String S, int[] P, int[] K) {
		char[] sc = S.toCharArray();
		int[] A = new int[sc.length];
		int[] G = new int[sc.length];
		int[] C = new int[sc.length];

		int prevA = -1, prevG = -1, prevC = -1;

		for (int i = 0; i < sc.length; i++) {
			if (sc[i] == 'A'){
				prevA = i;
				A[i] = prevA+1;
			}
			else if (sc[i] == 'G'){
				prevG = i;
				G[i] = prevG+1;				
			}
			else if (sc[i] == 'C'){
				prevC = i;
				C[i] = prevC+1;				
			}
		}
		int[] result = new int[P.length];

		for (int i = 0; i < P.length; i++) {
			if (A[K[i]] >= P[i] && A[K[i]] <= K[i]) {
				result[i] = 1;
			} else if (C[K[i]] >= P[i] && C[K[i]] <= K[i]) {
				result[i] = 2;
			} else if (G[K[i]] >= P[i] && G[K[i]] <= K[i]) {
				result[i] = 3;
			} else {
				result[i] = 4;
			}
		}
		return result;
	}

	public static int[] dnaImpact(String S, int[] P, int[] Q) {
		int[][] genoms = new int[3][S.length() + 1];
		short a, c, g;
		for (int i = 0; i < S.length(); i++) {
			a = 0;
			c = 0;
			g = 0;
			if ('A' == (S.charAt(i))) {
				a = 1;
			}
			if ('C' == (S.charAt(i))) {
				c = 1;
			}
			if ('G' == (S.charAt(i))) {
				g = 1;
			}
			genoms[0][i + 1] = genoms[0][i] + a;
			genoms[1][i + 1] = genoms[1][i] + c;
			genoms[2][i + 1] = genoms[2][i] + g;
		}

		int[] result = new int[P.length];
		for (int i = 0; i < P.length; i++) {
			int fromIndex = P[i];
			int toIndex = Q[i] + 1;
			if (genoms[0][toIndex] - genoms[0][fromIndex] > 0) {
				result[i] = 1;
			} else if (genoms[1][toIndex] - genoms[1][fromIndex] > 0) {
				result[i] = 2;
			} else if (genoms[2][toIndex] - genoms[2][fromIndex] > 0) {
				result[i] = 3;
			} else {
				result[i] = 4;
			}
		}

		return result;
	}

	public static int pasingCar(int[] a) {
		int ones = 0;
		int zeros = 0;
		int length = a.length;
		int count = 0;
		for (int i = 0; i < length; i++) {
			if (a[i] == 0)
				zeros++;
		}
		ones = length - zeros;
		for (int i = 0; i < length; i++) {
			if (a[i] == 0) {
				count += ones;
				if (count > 1000000000) {
					count = -1;
					break;
				}
			} else {
				ones--;
			}
		}
		if (count > 1000000000)
			count = -1;
		return count;
	}

	public static int countDiv(int a, int b, int k) {
		int count = b / k - a / k + (a % k == 0 ? 1 : 0);
		return count;
	}

}
