package DP;

import java.io.*;
import java.util.*;

public class b7579 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
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
				if (c >= cost[i]) {
					dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - cost[i]] + weight[i]);
				} else {
					dp[i][c] = dp[i - 1][c];
				}
			}
		}

		int answer = maxCost;
		for (int c = 0; c <= maxCost; c++) {
			if (dp[N][c] >= M) {
				answer = c;
				break;
			}
		}

		System.out.println(answer);
	}

}
