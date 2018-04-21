package com.lac.codility;

public class SotiTest2 {
	public static void main(String[] args) {
		int a[] = new int[]{5,5,1,7,2,3,5};
		//int a[] = new int[]{1,4,4,4,4,4,4};
		int index = solution(5,a);
		System.out.println(index);
	}
	
	
	static int solution(int X, int[] A) {
		int countEqual=0;
		int length = A.length;
		int index = 0;
		int countNotEqual=0;
		for(int i=0;i<length;i++){
			if(A[i]!=X) countNotEqual++;
		}
		
		for(int i=0;i<length;i++){
			if(A[i]==X){
				countEqual++;
			}else{
				countNotEqual--;
			}
			if(countEqual == countNotEqual)
				index = i+1;
		}
		return index;
    }
	
	static int solution2(int X, int[] A) {
		int countEqual=0;
		int length = A.length;
		int index = -1;
		for(int i=0;i<length;i++){
			if(A[i]==X) countEqual++;
			int countNotEqual=0;
			for(int j=length-1;j>i;j--)
			{
				if(A[j]!=X) countNotEqual++;
			}
			if(countEqual==countNotEqual)
				index = i;
		}
		return index;
    }
	
	
}
