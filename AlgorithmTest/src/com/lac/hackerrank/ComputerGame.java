package com.lac.hackerrank;

public class ComputerGame {

	public static void main(String[] args) {
		
	}
	
	public static int greatestCommonDivisor(int a,int b){
		int result = 0;
		if(a % b == 0){
			result = b;
		}else{
			result = greatestCommonDivisor(b,a%b);
		}
		return result;
	}
}
