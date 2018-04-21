package com.lac.codility;

public class CodilityMaximum {

	public static void main(String[] args) {
		int a[] = {3,2,-6,4,0};
		//int a[] = {-20};
		int result = maxSliceSum(a);
		System.out.println(result);
	}
	
	public static int maxSliceSum(int []a){
		int maxEnding = a[0];
		int maxSlice = a[0];
		for(int i=1;i<a.length;i++){
			maxEnding = Math.max(a[i], maxEnding+a[i]);
			maxSlice = Math.max(maxEnding, maxSlice);
		}
		return maxSlice;
	}
	
	public static int maxDoubleSlice(int[] a){
		int length = a.length;
		int maxFromLeft[] = new int[length];
		int maxFromRigth[] = new int[length];
		int maxSumLeft = 0,maxSumRight=0;
		for(int i=1;i<length-1;i++){
			maxSumLeft = Math.max(0, maxSumLeft+a[i]);
			maxFromLeft[i] = maxSumLeft;
		}
		for(int i=length-2;i>0;i--){
			maxSumRight = Math.max(0, maxSumRight+a[i]);
			maxFromRigth[i]=maxSumRight;
		}
		int maxDoubleSlice = 0;
		for(int i=1;i<length-1;i++){
			maxDoubleSlice = Math.max(maxDoubleSlice, maxFromLeft[i-1]+maxFromRigth[i+1]);
		}
		return maxDoubleSlice;
	}
	
	public static int maximunSlice(int a[]){
		int max_slice = 0;
		int max_ending = 0;
		for(int i:a){
			max_ending  = Math.max(0,max_ending+i);
			max_slice = Math.max(max_slice, max_ending);
		}
		return max_slice;
	}
	
	public static int profit(int a[]){
		if(a == null ||a.length==0) return 0;
		int length = a.length;
		int gap[] = new int[length-1];
		for(int i=0;i<length-1;i++){
			int profit = a[i+1]-a[i];
			gap[i] = profit;
		}
		int maxProfit = maximunSlice(gap);
		return maxProfit>0?maxProfit:0;
	}
}
