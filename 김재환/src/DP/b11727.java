package DP;

import java.io.*;
import java.util.*;

public class b11727 {

	static int[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		if(N == 1) {
			System.out.println(1);
			return;
		}
		memo = new int[N + 1];
		memo[0] = 0;
		memo[1] = 1;
		memo[2] = 3;

		for (int i = 3; i <= N; i++) {
			f(i);
		}
		
		System.out.println(memo[N]);
	}
	
	private static int f(int n) {
		memo[n] = (memo[n-1] + (memo[n-2] * 2))%10007;
		return memo[n];
	}

}
