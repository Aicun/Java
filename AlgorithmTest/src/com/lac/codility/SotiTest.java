package com.lac.codility;

public class SotiTest {
	public static void main(String[] args) {
		int a[] = new int[]{1,3,1, 2, 3, 3, 1, 3, 1,3,2,2,2,2,2};
		//int a[] = new int[]{1,2,2};
		System.out.println(solution(3, a));
	}

	static int solution(int M, int[] A) {
        int N = A.length;
        int[] count = new int[M + 1];
        for (int i = 0; i <= M; i++)
            count[i] = 0;
        int maxOccurence = 1;
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (count[A[i]] > 0) {
                int tmp = count[A[i]];
                if (tmp >= maxOccurence) {
                    maxOccurence = tmp;
                    index = i;
                }
                count[A[i]] = tmp + 1;
            } else {
                count[A[i]] = 1;
            }
        }
        return A[index];
    }
}
