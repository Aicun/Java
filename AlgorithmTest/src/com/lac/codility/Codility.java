package com.lac.codility;

import java.util.Stack;

public class Codility {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		int[] A = new int[]{-1,3,-4,5,1,-6,2,1};
		int result = Codility.solution(A);
		System.out.println("the result is"+ result);
	}
	
	public static int stoneWall(int[] H){
	          int len = H.length;
	          Stack<Integer> stack = new Stack<Integer>();
	          int blockCounter = 0;
	 
	          for (int i = 0; i < len; ++i) {
	              int element = H[i];
	              if (stack.isEmpty()) {
	                  stack.push(element);
	                  ++blockCounter;
	              } else {
	                  while (!stack.isEmpty() && stack.peek() > element) {
	                      stack.pop();
	                  }
	                  if (!stack.isEmpty() && stack.peek() == element) {
	                     continue;
	                  } else {
	                      stack.push(element);
	                      ++blockCounter;
	                  }
	              }
	          }
	 
	          return blockCounter;
	}
	
	public static int solution(int[] A) {
        // write your code in Java SE 8
		int result = -1;
		int length = A.length;
		long sumA= 0;
		long sumB = 0;
		long sum = 0;
		for(int i=0;i<length;i++){
			sum+=A[i];
		}
        for(int i=0;i<length;i++){
        	if(i>0){
        		sumA +=A[i-1];
        	}
        	if(i<length-1){
        		sumB = sum-sumA-A[i];
        	}else{
        		sumB=0;
        	}
        	if(sumA == sumB){
        		result = i;
        	}
        }
        return result;
    }

}
