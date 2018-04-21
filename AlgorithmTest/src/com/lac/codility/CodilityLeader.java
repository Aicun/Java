package com.lac.codility;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CodilityLeader {

	public static void main(String[] args) {
		//int[] a = {4, 4, 2, 5, 3, 4, 4, 4};
		int a[] = {4,3,4,4,4,2};
		int count = equiLeader(a);
		System.out.println(count);		
	}
	
	public static int equiLeader(int []a){
		int length = a.length;
		int count =0;
		int value = 0;
		for(int i=0;i<length;i++){
			if(count==0){
				count++;
				value = a[i];
			}else{
				if(a[i]!=value){
					count--;
				}else{
					count++;
				}
			}
		}
		int number = 0;
		if(count>0){
			for(int i=0;i<length;i++){
				if(a[i]==value){
					//list.add(i);
					number++;
				}
			}
		}
		int equiLeaderCount = 0;
		if(number > length/2){
			int leftCount = 0;
			int rightCount = 0;
			int leftElements=0;
			int rightElements=0;
			for(int i=0;i<length;i++){
				if(a[i]==value){
					leftCount++;
					rightCount = number-leftCount;
				}
				leftElements++;
				rightElements = length-leftElements;
				if(leftCount>leftElements/2 && rightCount>rightElements/2){
					equiLeaderCount++;
				}
			}
		}
		return equiLeaderCount;
	}
	
	public static int dominator2(int a[]){
		int length = a.length;
		int count =0;
		int value = 0;
		for(int i=0;i<length;i++){
			if(count==0){
				count++;
				value = a[i];
			}else{
				if(a[i]!=value){
					count--;
				}else{
					count++;
				}
			}
		}
		int number = 0;
		int index = -1;
		if(count>0){
			for(int i=0;i<length;i++){
				if(a[i]==value){
					number++;
					index = i;
				}
			}
		}
		if(number <= length/2){
			index = -1;
		}
		return index;
	}
	
	public static int dominator(int[] a){
		Stack<Integer> stack = new Stack<Integer>();
		int length = a.length;
		int index = -1;
		for(int i=0;i<length;i++){
			if(stack.size()==0){
				stack.push(a[i]);
			}else{
				int previous = stack.peek();
				if(a[i]!=previous){
					stack.pop();
				}else{
					stack.push(a[i]);
				}
			}
		}
		int count = 0;
		if(stack.size()>0){
			int dominator = stack.pop();
			for(int i=0;i<length;i++){
				if(a[i]==dominator){
					index = i;
					count ++;
				}
			}
			if(count <= length/2){
				index = -1;
			}
		}
		return index;
	}
}
