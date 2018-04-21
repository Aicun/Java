package com.lac.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AcmTeam {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        String topic[] = new String[n];
        for(int topic_i=0; topic_i < n; topic_i++){
            topic[topic_i] = in.next();
        }
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int max = 0;
        for(int i=0;i<topic.length-1;i++){
        	String a = topic[i];
        	for(int j=i+1;j<topic.length;j++){
        		String b = topic[j];
        		int count =0;
        		for(int l=0;l<m;l++){
        			if(a.charAt(l)=='1' || b.charAt(l)=='1'){
        				count++;
        			}
        		}
        		max = Math.max(max, count);
        		Integer exist = map.get(count);
        		if(exist == null){
        			map.put(count, 1);
        		}else{
        			map.put(count, exist+1);
        		}
        	}
        }
        System.out.println(max);
        System.out.println(map.get(max));
		
		/*Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(1, 2);
		System.out.println(map.get(0));*/
	}
}
