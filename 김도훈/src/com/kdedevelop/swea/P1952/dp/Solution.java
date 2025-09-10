package com.kdedevelop.swea.P1952.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	long result;
	int[] PAY;
	int[] PLAN;
	int[] dp;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputPay = new StringTokenizer(br.readLine());
			StringTokenizer inputPlan = new StringTokenizer(br.readLine());
			PAY = new int[4];
			PLAN = new int[13];
			dp = new int[13];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[0] = 0;
			for(int i = 0 ; i < 4 ; i ++) {
				int value = Integer.parseInt(inputPay.nextToken());
				PAY[i] = value;
			}
			for(int i = 1 ; i <= 12 ; i ++) {
				int value = Integer.parseInt(inputPlan.nextToken());
				PLAN[i] = value;
			}
			
			for(int i = 1 ; i <= 12 ; i ++) {
				dp[i] = Math.min(dp[i], dp[i - 1] + PLAN[i] * PAY[0]);
				dp[i] = Math.min(dp[i], dp[i - 1] + PAY[1]);
				dp[i] = Math.min(dp[i], dp[Math.max(0, i - 3)] + PAY[2]);
				dp[i] = Math.min(dp[i], dp[Math.max(0, i - 12)] + PAY[3]);
			}
			
//			function(0, 0);
			
//			System.out.println(Arrays.toString(PAY));
//			System.out.println(Arrays.toString(PLAN));
//			System.out.println(Arrays.toString(dp));
			
			result = dp[12];
			
			bw.write("#" + (testCase + 1) + " " + result + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
