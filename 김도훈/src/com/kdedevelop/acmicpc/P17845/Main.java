package com.kdedevelop.acmicpc.P17845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int function() {
		int[][] dp = new int[K + 1][N + 1];
		
		for(int i = 1 ; i <= K ; i ++) {
			int[] subject = SUBJECTs[i - 1];
			int important = subject[0];
			int time = subject[1];
			for(int j = 0 ; j <= N ; j ++) { 
				if(j < time) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - time] + important);
				}
			}
		}
		
//		for(int i = 0 ; i <= K ; i ++) {
//			for(int j = 0 ; j <= N ; j ++) {
//				System.out.print(String.format("%5d", dp[i][j]));
//			}
//			System.out.println("");
//		}
//		System.out.println("============");
		
		return dp[K][N];
	}
	
	int N, K;
	int[][] SUBJECTs;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		SUBJECTs = new int[K][];
		for(int i = 0 ; i < K ; i ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			int I = Integer.parseInt(lineInput.nextToken());
			int T = Integer.parseInt(lineInput.nextToken());
			
			int[] subject = new int[] {I, T};
			SUBJECTs[i] = subject;
		}
		
		int result = function();
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
