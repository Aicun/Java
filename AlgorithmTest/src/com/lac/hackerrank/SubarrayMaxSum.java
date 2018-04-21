package com.lac.hackerrank;

public class SubarrayMaxSum {
	
	public static void main(String[] args) {
		int [] a = {-2, -3, -4, -1, -2, -1, -5, -3};
		
		int[] result = maxSubArraySum(a);
        System.out.format("Maximum contiguous sum is %d, %d, %d", result[0], result[1], result[2]);
	}
	
	static int[] maxSubArraySum(int a[]) {
		int max = a[0];
		int currentSum = a[0];
		int length = a.length;
		
		int start = 0, end = 0;
		
		for(int i=1; i<length; i++) {
			currentSum += a[i];
			
			if(max < currentSum) {
				end = i;
				max = currentSum;
			}
			
			if(currentSum < 0) {
				start = i + 1;
				currentSum = 0;
			}
		}
		
		if (start > end)
			start = end;
		
		return new int[] {max, start, end};
	}

}
