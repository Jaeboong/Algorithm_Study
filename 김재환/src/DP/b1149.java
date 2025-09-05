package DP;

import java.io.*;
import java.util.*;

public class b1149 {
	
	static final int R = 0;
	static final int G = 1;
	static final int B = 2;
	
	static int [][] cost;
	static int [][] dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		cost = new int[N][3];
		dp = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][R] = Integer.parseInt(st.nextToken());
			cost[i][G] = Integer.parseInt(st.nextToken());
			cost[i][B] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][R] = cost[0][R];
		dp[0][G] = cost[0][G];
		dp[0][B] = cost[0][B];
		
		for(int i=1; i<N; i++) {
			dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + cost[i][R];
			dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + cost[i][G];
			dp[i][B] = Math.min(dp[i-1][G], dp[i-1][R]) + cost[i][B];
		}
		
		int tmp1 = Math.min(dp[N-1][R], dp[N-1][G]);
		int tmp2 = Math.min(dp[N-1][G], dp[N-1][B]);
		int ans = Math.min(tmp1, tmp2);
		System.out.println(ans);
	}

}
