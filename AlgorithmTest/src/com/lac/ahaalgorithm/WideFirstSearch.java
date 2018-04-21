package com.lac.ahaalgorithm;

import java.util.LinkedList;
import java.util.Queue;

public class WideFirstSearch {

	private static int a[][] = { { 1, 2, 1, 0, 0, 0, 0, 0, 2, 3 },
			{ 3, 0, 2, 0, 1, 2, 1, 0, 1, 2 }, { 4, 0, 1, 0, 1, 2, 3, 2, 0, 2 },
			{ 3, 2, 0, 0, 0, 1, 2, 4, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 5, 3, 0 },
			{ 0, 1, 2, 1, 0, 1, 5, 4, 3, 0 }, { 0, 1, 2, 3, 1, 3, 6, 2, 1, 0 },
			{ 0, 0, 3, 4, 8, 9, 7, 5, 0, 0 }, { 0, 0, 0, 3, 7, 8, 6, 0, 1, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 } };
	private static int book[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private static int m = 10, n=10;
	private static int sum = 1;
	
	private static Queue<Node> queue = new LinkedList<Node>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		wfs(6,8);
		System.out.println(sum);
	}

	public static void wfs(int x, int y) {
		int next[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		
		Node tail = new Node(x,y);
		book[x][y] = 1;
		queue.add(tail);

		while(queue.peek()!=null) {
			Node node = queue.poll();
			
			for (int i = 0; i <= 3; i++) {
				int nextx = node.x + next[i][0];
				int nexty = node.y + next[i][1];
				
				if(nextx < 0 || nextx >n-1 ||  nexty < 0 || nexty > m-1) 
					continue;
				if(a[nextx][nexty] > 0 && book[nextx][nexty] == 0) {
					sum ++;
					book[nextx][nexty] = 1;
					Node n = new Node(nextx,nexty);
					queue.add(n);
				}
			}
		}
	}
}

class Node {
	int x;
	int y;
	
	public Node(int x,int y){
		this.x = x;
		this.y = y;
	}
}
