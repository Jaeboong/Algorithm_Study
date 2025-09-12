package DP;

import java.io.*;
import java.util.*;

public class b17845 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[K + 1][N + 1];
		int[] I = new int[K + 1];
		int[] time = new int[K + 1];

		for (int i = 1; i <= K; i++) {
			st= new StringTokenizer(br.readLine());
			I[i] = Integer.parseInt(st.nextToken());
			time[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= K; i++) {
			for (int t = 0; t <= N; t++) {
				dp[i][t] = dp[i-1][t];
				if(t>=time[i]) {
					dp[i][t] = Math.max(dp[i][t], dp[i-1][t-time[i]] + I[i]);
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
