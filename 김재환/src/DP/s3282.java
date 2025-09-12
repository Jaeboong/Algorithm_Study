package DP;

import java.io.*;
import java.util.*;

public class s3282 {
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("TEST.txt"));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		
		for(int testcase = 1; testcase <= T; testcase++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[][] dp = new int[N+1][K+1];
			int[] V = new int[N+1];
			int[] C = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				V[i] = sc.nextInt();
				C[i] = sc.nextInt();
			}
			
			for(int i=1; i<=N; i++) {
				for(int k=0; k<=K; k++) {
					dp[i][k] = dp[i-1][k];
					if(k-V[i] >= 0) {
						dp[i][k] = Math.max(dp[i][k], dp[i-1][k-V[i]] + C[i]);
					}
				}
			}
			sb.append("#").append(testcase).append(" ").append(dp[N][K]).append("\n");
		}
		
		System.out.println(sb);
	}

}
