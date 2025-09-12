package DP;

import java.io.*;
import java.util.*;

public class s5215 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int T = sc.nextInt();

		for (int testcase = 1; testcase <= T; testcase++) {
			int N = sc.nextInt();
			int L = sc.nextInt();

			int[][] dp = new int[N + 1][L+1];
			int[] weight = new int[N + 1];
			int[] value = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				value[i] = sc.nextInt();
				weight[i] = sc.nextInt();
			}

			for (int i = 1; i <= N; i++) {
				for (int w = 0; w <= L; w++) {
					dp[i][w] = dp[i - 1][w];
					if (w - weight[i] >= 0 && dp[i][w] < dp[i - 1][w - weight[i]] + value[i]) {
						dp[i][w] = dp[i - 1][w - weight[i]] + value[i];
					}
				}
			}
			
			sb.append("#").append(testcase).append(" ").append(dp[N][L]).append("\n");
		}
		
		System.out.println(sb);
	}

}
