package DP;

import java.io.*;
import java.util.*;

public class fibonacci {

	static long[] memo = new long[2];
	static long[] memo2;
	static int N = 10;

	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		memo[0] = 0;
		memo[1] = 1;
		memo2 = new long[N + 1];
		long start = System.currentTimeMillis();
//		for (int i = 2; i <= N; i++) {
//			memo[i % 2] = memo[(i - 1) % 2] + memo[(i - 2) % 2];
//		}
//
//		System.out.println(memo[N % 2]);

		memo2[0] = 0;
		memo2[1] = 1;

		for (int i = 2; i <= N; i++) {
			fib(i);
		}
//		System.out.println(fib(50));
		System.out.println(memo2[N]);
		long end = System.currentTimeMillis();
		System.out.println("실행시간: " + (end - start) + "ms");
	}

	private static long fib(int n) {
		return memo2[n] = memo2[n - 1] + memo2[n - 2];
	}
}
