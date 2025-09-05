package DP;

import java.io.*;
import java.util.*;

public class b2133 {

	static long[] memo;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		memo = new long[N + 1];

		if (N % 2 == 1) {
			System.out.println(0);
			return;
		}

		if (N == 2) {
			System.out.println(3);
			return;
		}

		if (N == 4) {
			System.out.println(11);
			return;
		}
		memo[0] = 1;
		memo[2] = 3;

		for (int i = 4; i <= N; i += 2) {
			f(i);
		}

		System.out.println(memo[N]);

	}

	private static long f(int n) {
		memo[n] = 0;
		memo[n] += memo[n - 2] * memo[2];
		for (int i = 4; i <= n; i += 2) {
			memo[n] += memo[n - i] * 2;
		}
		return memo[n];
	}

}
