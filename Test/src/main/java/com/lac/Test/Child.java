package com.lac.Test;

import java.util.ArrayList;
import java.util.List;


public class Child implements Runnable {
	private int k;
	private int h;
	
	private static List<String> ids = new ArrayList<String>();
	
	public Child(int k,int h){
		this.k=k;
		this.h=h;
	}

	public static void main(String args[]){
		int interval = 10;
		int size = 101;
		int start;
		int end;
		for(int j=0;j<101;j++){
			ids.add("abc"+j);
		}
		for(int i=0;i<10;i++){
			start = i*interval+1;
			if(i!=9){
				end = (i+1)*interval;
			}else{
				end = size;
			}
			Child c = new Child(start,end);
			Thread t = new Thread(c);
			t.start();
		}
	}

	@Override
	public void run() {
		for(int i=k;i<=h;i++){
			System.out.println(ids.get(i-1));
			}
		}
}
