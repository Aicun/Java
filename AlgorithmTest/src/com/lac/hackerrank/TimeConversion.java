package com.lac.hackerrank;

import java.util.Scanner;

public class TimeConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		convert();;
	}

	public static void convert() {
		Scanner in = new Scanner(System.in);
		String time = in.next();
		String formated = time;
		if (time.contains("AM")) {
			if (time.startsWith("12")) {
				formated = time.replaceFirst("12", "00");
			}
		} else {
			if (!time.startsWith("12")) {
				String hour = time.substring(0,2);
				int h = Integer.valueOf(hour);
				h = h + 12;
				formated = time.replaceFirst(hour, String.valueOf(h));
			}
		}
		formated = formated.substring(0,time.length() - 2);
		System.out.println(formated);
	}

}
