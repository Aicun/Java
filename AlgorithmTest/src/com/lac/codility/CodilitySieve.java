package com.lac.codility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CodilitySieve {
	public static void main(String[] args) {
		// int[] seive = seive(20);
		// int factors = factorization(20);
		// String result = Arrays.toString(seive);
		int n = 26;
		int p[] = { 1, 4, 16 };
		int q[] = { 26, 10, 20 };
		int a[] = {3,1,2,3,6};
		int result[] = countNonDivisible(a);
		System.out.println(Arrays.toString(result));
	}

	public static int[] countNonDivisible(int[] a){
		int length = a.length;
		int max = getMax(a);
		int counts[] = numberOccurence(a,max);
		int results[] =new int[length];
		for(int i=0;i<length;i++){
			int count = length;
			int j;
			for(j=1;j<Math.sqrt(a[i]);j++){
				if(a[i]%j!=0){
					continue;
				}
				count -= counts[j];
				count -= counts[a[i]/j];
			}
			if(j==Math.sqrt(a[i])){
				count -= counts[j];
			}
			results[i] = count;
		}
		return results;
	}
	
	private static int getMax(int[] a){
		int max = a[0];
		for(int i=1;i<a.length;i++){
			max = Math.max(max, a[i]);
		}
		return max;
	}
	
	private static int[] numberOccurence(int[] a, int max){
		int counts[] = new int[max+1];
		for(int i=0;i<a.length;i++){
			counts[a[i]]++;
		}
		return counts;
	}

	public static int[] semiprimes(int n, int p[], int q[]) {
		int resultLength = p.length;
		int results[] = new int[resultLength];
		int primes[] = seive(n);
		int semiPrimes[] = new int[n + 1];
		/*
		 * for(int i=0;i<primes.length;i++){ int primei = primes[i];
		 * if(primei==1){ for(int j=i;j<primes.length;j++){ int primej =
		 * primes[j]; if(primej==1){ int index = i*j; if(index<=n){
		 * semiPrimes[index] = 1; } } } } }
		 */

		int h = 2;
		while (h * h <= n) {
			if (primes[h] == 0) {
				h++;
				continue;
			}
			int k = h;
			while (k * h <= n) {
				if (primes[k] == 1) {
					semiPrimes[k * h] = 1;
				}
				k++;
			}
			h++;
		}

		for (int i = 1; i <= n; i++) {
			semiPrimes[i] += semiPrimes[i - 1];
		}

		for (int i = 0; i < resultLength; i++) {
			/*
			 * int start = p[i]; int end = q[i]; int semiCount = 0; for(int
			 * j=start;j<=end;j++){ if(semiPrimes[j] == 1){ semiCount++; } }
			 * results[i]= semiCount;
			 */
			results[i] = semiPrimes[q[i]] - semiPrimes[p[i] - 1];
		}
		return results;
	}

	// for a given number,figure out the prime numbers
	public static int[] seive(int n) {
		int seive[] = new int[n + 1];
		Arrays.fill(seive, 1);
		// 0 and 1 are not prime numbers
		seive[0] = seive[1] = 0;
		int i = 2;
		// starts from two, cross out multiples of i which are >= i*i
		while (i * i <= n) {
			if (seive[i] == 1) {
				int k = i * i;
				while (k <= n) {
					seive[k] = 0;
					k += i;
				}
			}
			i++;
		}
		return seive;
	}

	public static int factorization(int n) {
		int f[] = new int[n + 1];
		int i = 2;
		while (i * i <= n) {
			if (f[i] == 0) {
				int k = i * i;
				while (k <= n) {
					// K might have serveral prime factors, make sure its
					// smallest prime factor
					// is set here
					if (f[k] == 0) {
						f[k] = i;
					}
					k += i;
				}
			}
			i++;
		}
		for (int j = 2; j < 20; j++) {
			int k = j;
			Set<Integer> primeFactors = new HashSet<Integer>();
			// for each k, if f[k]>0, means it has prime factors,and the
			// smallest is f[k]
			// divide k by f[k], then check whether the result k/f[k] has prime
			// factors.
			while (f[k] > 0) {
				primeFactors.add(f[k]);
				k = k / f[k];
			}
			primeFactors.add(k);
			System.out.println(k);
		}
		return 0;
	}
}
