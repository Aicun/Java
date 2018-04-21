package com.lac.mr.wc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	public static void main(String[] args) throws IOException {
		String line;
		File f = new File("D:\\eclipse\\MapReduce\\src\\com\\lac\\mr\\wc\\WordCount.java");
		//InputStream is = new FileInputStream(f);
		FileReader fr = new FileReader(f);
		BufferedReader br= new BufferedReader(fr);
		int count = 0 ;
		 while ((line = br.readLine()) != null) {
			 count ++;
			 if(count>8 && count<=113) {
				//line = line.trim();
				//line = line.substring(3);
				line=line.trim();
				if(line.startsWith("."))
					line = line.substring(1);
				System.out.println(line);
			 }
		 }
	}
}
