package DP;

import java.io.*;
import java.util.*;

public class b11726 {

	static long[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (1 == N) {
			System.out.println(1);
			return;
		}
		if (2 == N) {
			System.out.println(2);
			return;
		}

		memo = new long[N + 1];
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 2;
		
		for(int i=3; i<=N; i++) {
			f(i);
		}
		
		System.out.println(memo[N]);
	}

	private static long f(int n) {
		memo[n] = (memo[n - 1] + memo[n - 2]) % 10007;
		return memo[n];
	}

}
