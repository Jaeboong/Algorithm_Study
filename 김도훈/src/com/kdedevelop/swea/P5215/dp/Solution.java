package com.kdedevelop.swea.P5215.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	long result;
	int N, L;
	int[][] FOODs;
	int[][] dp;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputNL = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNL.nextToken());
			L = Integer.parseInt(inputNL.nextToken());
			FOODs = new int[N][];
			dp = new int[N + 1][L + 1];
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int tasty = Integer.parseInt(inputLine.nextToken());
				int kalory = Integer.parseInt(inputLine.nextToken());
				
				int[] food = new int[] {tasty, kalory};
				
				FOODs[i] = food;
			}
			
			for(int i = 1 ; i <= N ; i ++) {
				for(int j = 1 ; j <= L ; j ++) {
					if(FOODs[i - 1][1] > j) {
						dp[i][j] = dp[i - 1][j];
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - FOODs[i - 1][1]] + FOODs[i - 1][0]);
					}
				}
			}
			
//			for(int i = 0 ; i <= N ; i ++) {
//				for(int j = 0 ; j <= L ; j ++) {
//					System.out.print(String.format("%4d", dp[i][j]));
//				}
//				System.out.println("");
//			}
//			System.out.println("=======================");
			
			int result = dp[N][L];
			
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
