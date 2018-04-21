package com.lac.codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodilityFibonacci {

	public static void main(String[] args) {
		int a[] = {4,4,5,5,1};
		int b[] = {3,2,4,3,1};
		int result[] = ladder(a,b);
		System.out.print(Arrays.toString(result));
		System.out.print(10&7);
	}
	
	
	/** To climb to A[i] rungs, you could either
    come from A[i]-1 with an one-step jump
    OR come from A[i]-2 with a two-step jump
    So from A[i] rungs, the number of different ways of climbing
    to the top of the ladder is the Fibonacci number at position
    A[i] + 1
	**/
	public static int[] ladder(int a[],int b[]){
		int length = a.length;
		int result[] = new int[length];
		int fib[] = new int[length+2];
		fib[0] = 0;
		fib[1] = 1;
		int max =b[0];
		for(int i=1 ;i<b.length;i++){
			if(b[i]>max){
				max = b[i];
			}
		}
		int mod = (1<<max)-1;
		for(int i=2;i<fib.length;i++){
			fib[i] = (fib[i-1]+fib[i-2])&mod;//to prevent Fibonacci numbers cause a memory overflow 
		}
		
		for(int i=0;i<length;i++){
			b[i] = (1<<b[i])-1;
		}
		
		for(int i=0;i<length;i++){
			result[i] = fib[a[i]+1] & b[i];
		}
		return result;
	}

	public static int fibFrog(int a[]) {
		int length = a.length;
		List<Integer> fibs = getFibonaci(length);
		boolean[] accessed = new boolean[length];
		List<Jump> jumps = new ArrayList<Jump>();
		jumps.add(new Jump(-1, 0));
		Jump cj = null;
		int step = 0;
		while (true) {
			if (step == jumps.size()) {
				return -1;
			}
			cj = jumps.get(step);
			step++;
			for (int f : fibs) {
				if (cj.position + f == length) {
					return cj.counter + 1;
				} else if (cj.position + f > length || a[cj.position + f] == 0
						|| accessed[cj.position + f]) {
					continue;
				}
				jumps.add(new Jump(cj.position + f, cj.counter + 1));
				accessed[cj.position + f] = true;
			}
		}
	}

	public static List<Integer> getFibonaci(int max) {
		List<Integer> fibs = new ArrayList<Integer>();
		fibs.add(1);
		fibs.add(1);
		int f = 1;
		while (fibs.get(f) <= max) {
			fibs.add(fibs.get(f) + fibs.get(f - 1));
			f++;
		}
		fibs.remove(0);
		//Collections.reverse(fibs);
		return fibs;
	}

	static class Jump {
		int position;
		int counter;

		Jump(int position, int counter) {
			this.position = position;
			this.counter = counter;
		}
	}
}
