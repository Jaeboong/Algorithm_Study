package DP;

import java.io.*;
import java.util.*;

public class b7579_re {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] weight = new int[N + 1];
		int[] cost = new int[N + 1];
		int maxCost = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			maxCost += cost[i];
		}

		int[][] dp = new int[N + 1][maxCost + 1];

		for (int i = 1; i <= N; i++) {
			for (int c = 0; c <= maxCost; c++) {
				dp[i][c] = dp[i - 1][c];
				if (c >= cost[i])
					dp[i][c] = Math.max(dp[i][c], dp[i - 1][c - cost[i]] + weight[i]);
			}
		}

		int ans = maxCost;
		for (int i = 0; i <= N; i++) {
			for (int c = 0; c <= maxCost; c++) {
				if (dp[i][c] >= M) {
					ans = c < ans ? c : ans;
				}
			}
		}

		System.out.println(ans);
	}

}
