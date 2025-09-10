package com.kdedevelop.acmicpc.P14852;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N;
	long[][] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new long[2][Math.max(3, N + 1)];
		Arrays.fill(dp[0], -1);
		dp[0][0] = 1;
		dp[1][0] = 2;
		dp[0][1] = 2;
		dp[1][1] = 6;
		dp[0][2] = 7;
		dp[1][2] = 20;
		
		
		for(int i = 3 ; i <= N ; i ++) {
			dp[0][i] = (dp[0][i - 1] * 2 + dp[0][i - 2] * 3) % 1000000007;
			
//			System.out.println("I :"+ i + " || " + dp[0][i]);
			
			dp[0][i] = (dp[0][i] + dp[1][i - 3]) % 1000000007;
			
			dp[1][i] = (dp[0][i] * 2 + dp[1][i - 1]) % 1000000007;
			
			
//			for(int j = i - 3 ; j >= 0 ; j --) {
//				dp[i] = (dp[i] + dp[j] * 2) % 1000000007;
//			}
		}
		
//		System.out.println(Arrays.toString(dp[0]));
//		System.out.println(Arrays.toString(dp[1]));
		
		long result = dp[0][N];
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
