package com.kdedevelop.acmicpc.P7579;
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
	
	public void functionIterator() {
		dp = new long[N + 1][sumOfCost + 1];
		
		long result = Integer.MAX_VALUE;
		
		for(int i = 1 ; i <= N ; i ++) {
			int memory = APPLICATIONs[i - 1][0];
			int cost = APPLICATIONs[i - 1][1];
			
			for(int j = 0 ; j <= sumOfCost ; j ++) {
				if(j < cost) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + memory);
				}
			}
		}
	}
	
	long[][] dp;
	int N, M;
	int[][] APPLICATIONs;
	int sumOfCost;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		
		APPLICATIONs = new int[N][];
		
		StringTokenizer inputMemory = new StringTokenizer(br.readLine());
		StringTokenizer inputCost = new StringTokenizer(br.readLine());
		
		sumOfCost = 0;
		
		for(int i = 0 ; i < N ; i ++) {
			int memory = Integer.parseInt(inputMemory.nextToken());
			int cost = Integer.parseInt(inputCost.nextToken());
			
			sumOfCost += cost;
			
			int[] application = new int[] {memory, cost};
			APPLICATIONs[i] = application;
		}
		
		
//		long[][] dp = new long[N + 1][M + 1];
//		for(int i = 0 ; i <= N ; i ++) {
//			Arrays.fill(dp[i], M);
//		}
//		
//		for(int i = 1 ; i <= N ; i ++) {
//			for(int j = 1 ; j <= M ; j ++) {
//				if(j < APPLICATIONs[i - 1][0]) {
//					dp[i][j] = dp[i - 1][j];
//				} else {
//					dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - APPLICATIONs[i - 1][0]] - APPLICATIONs[i - 1][1]);
//				}
//			}
//		}
		
		
		functionIterator();
		
		int result = 0;
		for(int cost = 0 ; cost <= sumOfCost ; cost ++) {
			if(dp[N][cost] >= M) {
				result = cost;
				break;
			}
		}
		
//		for(int i = 0 ; i <= N ; i ++) {
//			for(int j = 0 ; j <= sumOfCost ; j ++) {
//				System.out.print(String.format("%5d", (dp[i][j] == Integer.MAX_VALUE ? -1 : dp[i][j])));
//			}
//			System.out.println("");
//		}
//		System.out.println("===================");
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
