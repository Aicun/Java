package com.lac.hackerrank;

import java.util.Scanner;

public class Kangaroo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
        int x1 = in.nextInt();
        int v1 = in.nextInt();
        int x2 = in.nextInt();
        int v2 = in.nextInt();
        if(v1==v2) System.out.println("NO");
        else{
        	int i = (x2-x1)/(v1-v2);
            int t = (x2-x1)%(v1-v2);
            if(i>0 && t==0){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
	}

}
