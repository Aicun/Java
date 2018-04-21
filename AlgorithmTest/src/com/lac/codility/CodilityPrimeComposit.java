package com.lac.codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CodilityPrimeComposit {

	public static void main(String[] args) {
		// int factors = countFactors(24);
		int a[] = { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		//int a[] = {1, 3, 2, 1};
		int flags = peaksCount(a);
		System.out.println(flags);
	}
	
	public static int solution(int[] A) {
	    int N = A.length;

	    // Find all the peaks
	    ArrayList<Integer> peaks = new ArrayList<Integer>();
	    for(int i = 1; i < N-1; i++){
	      if(A[i] > A[i-1] && A[i] > A[i+1]) peaks.add(i);
	    }

	    for(int size = 1; size <= N; size++){
	      if(N % size != 0) continue; // skip if non-divisible
	      int find = 0;
	      int groups = N/size;
	      boolean ok = true;
	      // Find whether every group has a peak
	      for(int peakIdx : peaks){
	        if(peakIdx/size > find){
	          ok = false;
	          break;
	        }
	        if(peakIdx/size == find) find++;
	      }
	      if(find != groups) ok = false;
	      if(ok) return groups;
	    }
	    return 0;
	  }

	public static int peaksCount(int[] a) {
		int length = a.length;
		List<Integer> peaks = new ArrayList<Integer>();
	    for(int i = 1; i < length-1; i++){
	      if(a[i] > Math.max(a[i-1], a[i+1])) peaks.add(i);
	    }
		int blocksCount = 0;
		for(int i=1;i<=length;i++){
			if(length%i!=0) continue;
			int findWithinBlock = 0;
			int groups = length/i;
			boolean dividable = true;
			for(int peakIdx:peaks){
				if(peakIdx/i>findWithinBlock){
					dividable = false;
					break;
				}
				if(peakIdx/i == findWithinBlock){
					findWithinBlock++;
				}
			}
			if(findWithinBlock != groups){
				dividable = false;
			}
			
			if(dividable){
				blocksCount = Math.max(blocksCount, groups);
			}
		}
		return blocksCount;
	}

	private static int[] peaksPositions(int[] a) {
		int length = a.length;
		int[] peaks = new int[length];
		for (int i = 1; i < length - 1; i++) {
			if (a[i] > Math.max(a[i - 1], a[i + 1])) {
				peaks[i] = 1;
			}
		}
		return peaks;
	}

	public static int flags2(int[] a) {
		int length = a.length;
		int peaks[] = new int[length];
		for (int i = 1; i < length - 1; i++) {
			if (a[i] > Math.max(a[i - 1], a[i + 1])) {
				peaks[i] = 1;
			}
		}
		int next[] = new int[length];
		next[length - 1] = -1;
		for (int i = length - 2; i >= 0; i--) {
			if (peaks[i] == 1)
				next[i] = i;
			else
				next[i] = next[i + 1];
		}
		int i = 1;
		int result = 0;
		while ((i - 1) * i <= length) {
			int pos = 0;
			int num = 0;
			while (pos < length && num < i) {
				pos = next[pos];
				if (pos == -1)
					break;
				num++;
				pos += i;
			}
			result = Math.max(result, num);
			i++;
		}
		return result;
	}

	public static int flags(int[] a) {
		int length = a.length;
		int peaks[] = new int[length];
		for (int i = 1; i < length - 1; i++) {
			if (a[i] > Math.max(a[i - 1], a[i + 1])) {
				peaks[i] = 1;
			}
		}
		int maxFlags = 0;
		for (int i = 1; (i - 1) * i <= length; i++) {
			int flag = i;
			int pos = 0;
			while (pos < length && flag > 0) {
				if (peaks[pos] == 1) {
					flag--;
					pos += i;
				} else {
					pos++;
				}
			}
			if (flag == 0) {
				maxFlags = Math.max(maxFlags, i);
			}
		}
		return maxFlags;
	}

	public static int countFactors(int n) {
		int count = 0;
		int i = 1;
		int sqrt = (int) Math.sqrt(n);
		while (i <= sqrt) {
			if (n % i == 0) {
				count = count + 2;
			}
			i++;
		}
		if (sqrt * sqrt == n)
			count--;
		return count;
	}

	public static int minPerimeterRectangle(int n) {
		int i = 1;
		int min = Integer.MAX_VALUE;
		int perimeter = 0;
		while (i * i <= n) {
			if (n % i == 0) {
				int other = n / i;
				perimeter = 2 * (i + other);
				perimeter = Math.min(min, perimeter);
			}
			i++;
		}
		return perimeter;
	}

}
