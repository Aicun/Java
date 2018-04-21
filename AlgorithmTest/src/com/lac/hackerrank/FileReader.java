package com.lac.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public static void readFile() throws IOException{
		File file = new File("D:\\input.txt");
		java.io.FileReader fileReader = new java.io.FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		String line;
		String result;
		List<String> results = new ArrayList<String>();
		
		while((line = br.readLine()) != null){
			result = nextPermutation(line);
			results.add(result);
		}
		writeFile(results);
		br.close();
		fileReader.close();
	}
	
	public static void writeFile(List<String> results) throws IOException{
		File file = new File("D:\\output.txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		for(String result :results){
			bw.write(result);
			bw.newLine();
		}
		bw.close();
		fos.close();
	}
	
	public static String nextPermutation(String s){
		char[] letters = s.toCharArray();
		int j = letters.length - 1;
		while (j > 0 && letters[j - 1] >= letters[j]) {
			j--;
		}
		if (j <= 0) {
			s = "no answer";
		}else{
			
			int k = letters.length - 1;
			while (letters[k] <= letters[j - 1]) {
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
		}
		return s;
	}
}
