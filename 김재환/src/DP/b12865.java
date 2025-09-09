package DP;

import java.io.*;
import java.util.*;

public class b12865 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		
		int[] weight = new int[N + 1];
		int[] value = new int[N + 1];
		int maxValue = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
			maxValue += value[i];
		}
		
		int[][] dp = new int[N + 1][maxValue+1];
		for (int i = 0; i <= N; i++) {
		    for (int j = 1; j <= maxValue; j++) {
		        dp[i][j] = K+1; // 또는 K+1
		    }
		}

		for (int i = 1; i <= N; i++) {
			for (int v = 0; v <= maxValue; v++) {
				dp[i][v] = dp[i - 1][v];
				if (v >= value[i]) {
					dp[i][v] = Math.min(dp[i][v], dp[i - 1][v - value[i]] + weight[i]);
				}
			}
		}
		
		int ans = 0;
		
		for(int i=0; i<=N; i++) {
			for(int v=0; v<=maxValue; v++) {
				if(dp[i][v] <= K) {
					ans = v > ans ? v : ans;
				}
			}
		}
		
		System.out.println(ans);
	}

}
