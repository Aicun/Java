package com.lac.codility;

public class CodilityTest {

	public static void main(String[] args) {
		printAsterisks(4);
	}
	
	public static void printAsterisks(int n) {
		for(int i=n;i>0;i--){
			for(int j=0;j<n-i;j++){
				System.out.print(" ");
			}
			for(int j=0;j<2*i-1;j++){
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
