package com.kdedevelop.swea.P3282;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int[][] function() {
		int[][] dp = new int[N + 1][K + 1];
		
		for(int i = 1 ; i <= N ; i ++) {
			int v = THINGs[i - 1][0];
			int c = THINGs[i - 1][1];
			for(int j = 1 ; j <= K ; j ++) {
				if(v > j) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v] + c);
				}
			}
		}
		
		return dp;
	}
	
	long result;
	int N, K;
	int[][] THINGs;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputNK = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNK.nextToken());
			K = Integer.parseInt(inputNK.nextToken());
			
			THINGs = new int[N][];
			
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(inputLine.nextToken());
				int c = Integer.parseInt(inputLine.nextToken());
				
				int[] thing = new int[] {v, c};
				THINGs[i] = thing;
			}
			
			int[][] dp = function();
			
//			for(int i = 0 ; i <= N ; i ++) {
//				for(int j = 0 ; j <= K ; j ++) {
//					System.out.print(String.format("%3d", dp[i][j]));
//				}
//				System.out.println("");
//			}
//			System.out.println("============");
			
			result = dp[N][K];
			
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
