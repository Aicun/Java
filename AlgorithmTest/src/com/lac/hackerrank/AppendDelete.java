package com.lac.hackerrank;

import java.util.Scanner;

public class AppendDelete {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        String s = in.next();
        String t = in.next();
        int k = in.nextInt();
        int lengths = s.length();
        int lengtht = t.length();
        int i=0;
        for(i=0;i<lengths && i<lengtht;i++){
        	if(s.charAt(i)!=t.charAt(i))break;
        }
        
        int d = lengths-i;
        int a = lengtht-i;
        int p = k-d-a;
        boolean ok = false;
        if(p==0){
        	ok = true;
        }
        else if(p<0){
        	ok = false;
        }else{
        	if(p%2==0){
        		ok = true;
        	}else{
        		if(p>=(2*i))
        			ok = true;
        		else
        			ok = false;
        	}
        }
        System.out.println(ok?"YES":"NO");
	}
}
