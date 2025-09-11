package com.kdedevelop.acmicpc.P1149;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[][] COLOR = {{0, 1, 2}, {0, 2, 1}, {1, 0, 2}, {1, 2, 0}, {2, 0, 1}, {2, 1, 0}};
	
	public void functionIterator() {
		
	}
	
	int N;
	int[][] COST;
	int[][] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		COST = new int[N][N];
		dp = new int[N][3];
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < 3 ; x ++) {
				int value = Integer.parseInt(lineInput.nextToken());
				COST[y][x] = value;
			}
		}
		
		for(int i = 0 ; i < 3 ; i ++) {
			dp[0][i] = COST[0][i];
		}
		
		for(int i = 1 ; i < N ; i ++) {
			for(int j = 0 ; j < 3 ; j ++) {
				dp[i][j] = COST[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
			}
		}
		
//		for(int y = 0 ; y < N ; y ++) {
//			for(int x = 0 ; x < 3 ; x ++) {
//				System.out.print(String.format("%4d", dp[y][x]));
//			}
//			System.out.println("");
//		}
//		System.out.println("==================");
		
		int result = Integer.MAX_VALUE;
		for(int i = 0 ; i < 3 ; i ++) {
			result = Math.min(result, dp[N - 1][i]);
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
