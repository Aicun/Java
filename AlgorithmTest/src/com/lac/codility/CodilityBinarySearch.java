package com.lac.codility;

import java.util.Arrays;
import java.util.Comparator;

public class CodilityBinarySearch {

	public static void main(String[] args) {
		int a[] = {1,4,5,8};
		int b[] = {4,5,9,10};
		int c[] = {4,6,7,10,2};
		int result = nailingPlanks(a,b,c);
		System.out.println(result);
	}
	
	public static int minMaxDivision(int K,int M,int A[]){
		int lowerBound = arrayMax(A);
		int upperBound=  arraySum(A);
		int result = 0;
		if(K==1) return upperBound;
		if(K>=A.length) return lowerBound;
		while(lowerBound<=upperBound){
			int maxSearch = (lowerBound + upperBound)/2;
			int blockNeed = maxBlocks(A,maxSearch);
			if(blockNeed <= K){
				//it means we have spared blocks, so we can use these blocks
				//to decrease the maxSearch
				upperBound = maxSearch -1;
				result = maxSearch;
			}else{
				//it means we need more than K blocks,so maxSearch is not the 
				//answer, we need to increase it.
				lowerBound = maxSearch + 1;
			}
		}
		return result;
	}
	
	//from the first element of the array, sum the consecutive elements,
	//when reached the designated number, start over to continue,and we can figure
	//out the blocks needed
	private static int maxBlocks(int A[], int maxSearch){
		int blockNumber = 1;
		int presum = A[0];
		for(int i=1;i<A.length;i++){
			if(presum+A[i]>maxSearch){
				presum = A[i];
				blockNumber ++;
			}else{
				presum += A[i];
			}
		}
		return blockNumber;
	}
	
	private static int arrayMax(int A[]){
		int max = A[0];
		for(int i=1;i<A.length;i++){
			max = Math.max(max, A[i]);
		}
		return max;
	}
	
	private static int arraySum(int A[]){
		int sum = 0;
		for(int i=0;i<A.length;i++){
			sum += A[i];
		}
		return sum;
	}
	
	public static int nailingPlanks(int A[],int B[],int C[]){
		int upper = C.length;
		
		Integer[][] sortedNail = new Integer[upper][2];
        for (int i = 0; i < upper; i++) {
            sortedNail[i][0] = C[i];
            sortedNail[i][1] = i;
        }
        Arrays.sort(sortedNail,new Comparator<Integer[]>() {

			@Override
			public int compare(Integer[] a, Integer[] b) {
				return a[0]-b[0];
			}
		});
		
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result = nailed(A[i], B[i], sortedNail, result);
            if (result == -1)
                return -1;
        }
        return result + 1;
	}
	
	//from the sorted array, find out the mimimum position that is suitable for the 
	// specific range
	private static int nailed(int start,int end,Integer[][] nails,int preIndex){
		int resultPosition = -1;
		int lower = 0;
		int upper = nails.length-1;
		
		while(lower<=upper){
			int mid = (lower + upper)/2;
			int nailValue = nails[mid][0];
			if(nailValue<start){
				lower = mid +1;
			}else if(nailValue>end){
				upper = mid -1;
			}else{
				upper = mid - 1;
				resultPosition = mid;
			}
		}
		//no position is available
		if(resultPosition == -1){
			return resultPosition;
		}
		
		int minIndexOrigin = nails[resultPosition][1];
		for(int i=resultPosition+1;i<nails.length;i++){
			if(nails[i][0]>end){
				break;
			}
			minIndexOrigin  = Math.min(minIndexOrigin, nails[i][1]);
			//if the current position is <= previous position
			// return the previous, because the position should match
			// the previous range too
			if(minIndexOrigin<=preIndex){
				return preIndex;
			}
		}
		return minIndexOrigin;
	}
}

