package com.lac.troublesort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int n = in.nextInt();
			long[] values= new long[n];
			for(int j=0; j<n; j++) {
				long v = in.nextLong();
				values[j] = v;
			}
			
			int index = troubleSort(values);
			
			System.out.println("Case #" + i + ": " + (index == -1 ? "OK" : index));
		}
	}

	private static int troubleSort(long[] values) {
		bubbleSort(values);
		
		for(int i=0; i<values.length-1; i ++) {
			if(values[i] > values[i+1])
				return i;
		}
		return -1;
	}

	private static void bubbleSort(long[] values) {
		boolean done = false;
		while(!done) {
			done = true;
			for(int i=0; i< values.length-2; i++) {
				if(values[i] > values[i+2]) {
					done = false;
					long temp = values[i];
					values[i] = values[i+2];
					values[i+2] = temp;
				}
			}
		}
	}

}
