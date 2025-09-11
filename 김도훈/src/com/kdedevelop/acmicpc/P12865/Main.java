package com.kdedevelop.acmicpc.P12865;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int[][] function() {
		int[][] dp = new int[N + 1][K + 1];
		
		for(int i = 1 ; i <= N ; i ++) {
			int w = THINGs[i - 1][0];
			int v = THINGs[i - 1][1];
			for(int j = 1 ; j <= K ; j ++) {
				if(j < w) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
				}
			}
		}
		
		return dp;
	}
	
	int N, K;
	int[][] THINGs;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		THINGs = new int[N][];
		for(int i = 0 ; i < N ; i ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(lineInput.nextToken());
			int v = Integer.parseInt(lineInput.nextToken());
			int[] thing = new int[] {w, v};
			THINGs[i] = thing;
		}
		
		int[][] dp = function();
		
		int result = dp[N][K];
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
