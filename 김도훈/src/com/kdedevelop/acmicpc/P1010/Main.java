package com.kdedevelop.acmicpc.P1010;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void functionIter() {
		
//		for(int i = 2 ; i < 6 ; i ++) {
//			for(int j = i ; j < M ; j ++) {
//				dp[2][i] += dp[3][j];
//			}
//		}
		
		for(int i = N - 2 ; i >= 0 ; i --) {
			for(int j = 0 ; j < M ; j ++) {
				if(dp[i][j] == 0) continue;
				
				dp[i][j] = 0;
				for(int k = j + 1 ; k < M ; k ++) {
					dp[i][j] += dp[i + 1][k];
				}
			}
		}
		
//		for(int i = N - 2, start = diff - 1 ; i >= 0 ; i --, start --) {
//			for(int j = start, count = 0 ; count < N ; start ++, count ++) {
//				for(int k = start ; k < N ; k ++) {
//					dp[i][j] += dp[i + 1][k];
//				}
//			}
//		}
	}
	
	int TotalTestCase, N, M, diff;
	int[][] dp;
	public void solution() throws IOException {
		TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			diff = M - N + 1;
			dp = new int[N][M];
//			for(int i = 0 ; i < N ; i ++) {
//				Arrays.fill(dp[i], -1);
//			}
			for(int i = 0, count = 0; i < N ; i ++, count ++) {
				for(int j = count ; j < diff + count ; j ++) {
					dp[i][j] = -1;
				}
			}
			for(int i = 0 ; i < M ; i ++) {
				dp[N - 1][i] = 1;
			}
			
			functionIter();
			
//			for(int y = 0 ; y < N ; y ++) {
//				for(int x = 0 ; x < M ; x ++) {
//					System.out.print(String.format("%10d", dp[y][x]));
//				}
//				System.out.println("");
//			}
//			System.out.println("==========");
			
			int result = 0;
			for(int i = 0 ; i < M ; i ++) {
				result += dp[0][i];
			}
			
			bw.write(String.valueOf(result));
			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
