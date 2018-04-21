package com.lac.ahaalgorithm;

import java.util.LinkedList;
import java.util.Queue;

public class DeepFirstSearch {

	private static int a[][] = {
		{1,2,1,0,0,0,0,0,2,3},
		{3,0,2,0,1,2,1,0,1,2},
		{4,0,1,0,1,2,3,2,0,2},
		{3,2,0,0,0,1,2,4,0,0},
		{0,0,0,0,0,0,1,5,3,0},
		{0,1,2,1,0,1,5,4,3,0},
		{0,1,2,3,1,3,6,2,1,0},
		{0,0,3,4,8,9,7,5,0,0},
		{0,0,0,3,7,8,6,0,1,2},
		{0,0,0,0,0,0,0,0,1,0}
		};
	private static int book[][] = {
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0}};
	
	private static int m = 10, n=10;
	private static int sum = 1;
	
	public static void main(String[] args) {
		/*book[6][8] = 1;
		dfs(6,8,-1);
		System.out.println(sum);*/
		
		int num = 0;
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(a[i][j]>0) {
					num--;
					book[i][j] = 1;
					dfs(i,j,num);
				}
			}
		}
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(String.format("%3d", a[i][j]));
			}
			System.out.println();
		}
		
		System.out.println(-num);
	}
	
	public static void dfs(int x, int y, int s) {
		int next[][] = {{0,1},{1,0},{0,-1},{-1,0}};
		
		a[x][y] = s;
		for(int i=0;i<=3;i++) {
			int nextx = x + next[i][0];
			int nexty = y + next[i][1];
			
			if(nextx < 0 || nextx >n-1 ||  nexty < 0 || nexty > m-1) 
				continue;
			if(a[nextx][nexty] > 0 && book[nextx][nexty] == 0) {
				sum ++;
				book[nextx][nexty] = 1;
				dfs(nextx,nexty,s);
			}
		}
	}
}
