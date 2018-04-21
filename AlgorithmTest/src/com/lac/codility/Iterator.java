package com.lac.codility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.HashSet;

public class Iterator {
	public static void main(String[] args) {
		//int a[] = {9, 5, 7, 3, 2, 7, 3, 1, 10, 8};
		int a[]={3,4,4,6,1,4,4};
		int result[] = counter(5,a);
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
	}
	
	public static int[] counter(int n,int []a){
		int counter[] = new int[n];
		Arrays.fill(counter, 0);
		int length = a.length;
		int max = 0;
		int last = 0 ;
		int condition = n+1;
		for(int i=0;i<length;i++){
			int currentValue =a[i];
			if (currentValue == condition) {
				last = max;
			}else{
				int position = currentValue - 1;
				if(counter[position]<last)
					counter[position] = last+1;
				else
					counter[position]++;
				if(counter[position]>max) max = counter[position];
			}
		}
		
		for(int i=0;i<n;i++){
			if(counter[i]<last)
				counter[i] = last;
		}
		
		return counter;
	}
	
	public static int findSeconds(int x,int a[]){
		int length = a.length;
		int second = -1;
		Set<Integer> set = new HashSet<Integer>();
		for(int i=1;i<=x;i++){
			set.add(i);
		}
		for(int i=0;i<length;i++){
			if(set.contains(a[i]))
				set.remove(a[i]);
			if(set.isEmpty()){
				second = i;
				break;			
			}
		}
		return second;
	}
	
	public static int findMin(int A[]){
		int length = A.length;
		Arrays.sort(A);
		int position = 0;
		int result = 1;
		for(int i=0;i<length;i++){
			if(A[i]>0){
				position = i;
				break;
			}
		}
		for(int i=position;i<length;i++){
			if(A[i]==result){
				result++;
			}
		}
		return result;
	}
	
	public static int findPermutation(int []A){
		Arrays.sort(A);
		int length = A.length;
		for(int i=0;i<length-1;i++){
			if(A[i]==A[i+1])
				return 0;
		}
		if(A[length-1]!=length)
			return 0;
		return 1;
	}
	
	public static int findMissing(int A[]){
		int length = A.length;
		int missing = 0;
		if(length==0) missing = 1;
		else{
			Arrays.sort(A);
			for(int i=0;i<length;i++){
				if(A[i]!=(i+1)){
					missing = i+1;
					break;
				}
				if(i==length-1){
					missing = length+1;
				}
			}
		}
		return missing;
	}
	
	public static int steps(int X,int Y,int D){
		int gap = Y-X;
		int steps = (int) Math.ceil((double)gap/D);
		return steps;
	}
	
	public static int minimal(int A[]){
		int sumA  = 0;
		int length = A.length;
		int sumLeft = 0;
		int sumRight = 0;
		for (int i=0;i<length;i++){
			sumA +=A[i];
		}
		int []a = new int[length-1];
		for(int i=0;i<length-1;i++){
			sumLeft+=A[i];
			sumRight= sumA-sumLeft;
			int abs = Math.abs(sumLeft-sumRight);
			a[i] = abs;
		}
		
		Arrays.sort(a);
		return a[0];
	}
	
	public static int pair(int[] A){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		int result =-1;
		for(int i=0;i<A.length;i++){
			int value = A[i];
			if(map.containsKey(value)){
				int count = map.get(value);
				map.put(value, count+1);
			}else{
				map.put(value, 1);
			}
		}
		for(Entry<Integer, Integer> set:map.entrySet()){
			int count = set.getValue();
			if(count %2 == 1){
				result = set.getKey();
			}
		}
		return result;
	}
	
	public static int[] solution(int[] a, int k) {
		int length = a.length;
		if(length==0) return a;
		int move = k % length;
		if(move==0) return a;
		int[] temp = new int[move];
		for(int i=0;i<move;i++){
			temp[i] = a[length-move+i];
		}
		for(int i=length-move-1;i>=0;i--){
			a[i+move] = a[i];
		}
		for(int i=0;i<move;i++){
			a[i] = temp[i];
		}
		return a;
	}
	
	public static int binaryGap(int n){
		String binary = Integer.toBinaryString(n);
		System.out.println(binary);
		int length = binary.length();
		int gap = 0;
		int tempGap = 0;
		if(binary.lastIndexOf('1')==0)
			return gap;
		for(int i=1;i<length;i++){
			if(binary.charAt(i)=='0'){
				tempGap++;
			}
			else{
				if(tempGap>gap){
					gap = tempGap;
				}
				tempGap =0;
			}
		}
		return gap;
	}
}
