package com.lac.hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class BiggerGreater {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*try {
			FileReader.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		String s = FileReader.nextPermutation("zeye");
		System.out.print(s);
	}
	
	public static void exec(){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String s = in.next();
			char[] letters = s.toCharArray();
			int j = letters.length - 1;
			while (j > 0 && letters[j - 1] >= letters[j]) {
				j--;
			}
			if (j <= 0) {
				System.out.println("no answer");
				continue;
			}
			int k = letters.length - 1;
			while (letters[k] < letters[j - 1]) {
				k--;
			}

			char temp = letters[j - 1];
			letters[j - 1] = letters[k];
			letters[k] = temp;

			k = letters.length - 1;
			while (j < k) {
				temp = letters[k];
				letters[k] = letters[j];
				letters[j] = temp;
				j++;
				k--;
			}
			s = new String(letters);
			System.out.println(s);
		}		
	}
}
