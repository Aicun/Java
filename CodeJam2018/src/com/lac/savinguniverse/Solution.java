package com.lac.savinguniverse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			long n = in.nextLong();
			String m = in.next();
			
			long moves = calculateMoves(n, m);
			
			System.out.println("Case #" + i + ": " + (moves == -1 ? "IMPOSSIBLE" : moves));
		}
	}
	
	public static long calculateMoves(long withstand, String program) {
		
		long moves = 0;
		char[] cs = program.toCharArray();
		
		moves = moveProgram(cs,moves, withstand);
		
		return moves;
	}
	
	private static long moveProgram(char[] cs, long moves, long withstand) {
		
		long ps = countRobotShoots(cs);
		
		if(ps <= withstand) {
			return moves;
		}
		
		if(noMoreMoves(cs))
			return -1;
		
		moves ++;
		
		swapAdjacent(cs);
		
		return moveProgram(cs, moves, withstand);
	}

	private static boolean noMoreMoves(char[] cs) {
		String string = new String(cs);
		return string.lastIndexOf("S") == string.indexOf("C")-1 || string.indexOf("C") == -1 || string.indexOf("S") == -1;
	}

	private static void swapAdjacent(char[] cs) {
		for(int i= cs.length -1; i > 0; i--) {
			if(cs[i] =='S' && cs[i-1] =='C') {
				cs[i] ='C';
				cs[i-1] = 'S';
				break;
			}
		}
	}

	public static long countRobotShoots(char[] cs)
	{
		long count = 0;
		long shoot = 1;
		for (char c : cs) {
			if(c == 'C') {
				shoot = shoot *2;
			}
			if (c == 'S') {
				count += shoot;
			}
		}
		return count;
	}
}
