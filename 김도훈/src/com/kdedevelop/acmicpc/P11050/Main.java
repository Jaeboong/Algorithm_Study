package com.kdedevelop.acmicpc.P11050;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int function(int n, int k) {
		if(n < 0 || k < 0) return 0;
//		System.out.println("N : " + n + " || K : " + k);
		if(!visit[n][k]) {
			dp[n][k] = function(n - 1, k - 1) + function(n - 1, k);
			visit[n][k] = true;
		}
		return dp[n][k];
	}
	
	public int functionNew(int n, int k) {
		for(int y = 0 ; y <= N ; y ++) {
			for(int x = 0 ; x <= y ; x ++) {
//				System.out.println("Y : " + y + " || X : " + x);
				if(x == y || x == 0) {
					dp[y][x] = 1;
					continue;
				}
				dp[y][x] = dp[y - 1][x - 1] + dp[y - 1][x];
			}
		}
		
		return dp[n][k];
	}
	
	int N;
	int K;
	int[][] dp;
	boolean[][] visit;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		visit = new boolean[N + 1][N + 1];
		visit[0][0] = true;
		dp = new int[N + 1][N + 1];
		dp[0][0] = 1;
		
		int result = functionNew(N, K);
		
//		for(int y = 0 ; y <= N ; y ++) {
//			for(int x = 0 ; x <= N ; x ++) {
//				System.out.print(String.format("%3d", dp[y][x]));
//			}
//			System.out.println("");
//		}
//		System.out.println("=====");
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}