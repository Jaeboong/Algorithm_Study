package com.kdedevelop.acmicpc.P17069;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void functionIterator() {
		for(int x = 1 ; x < N ; x ++) {
			for(int y = 0 ; y < N ; y ++) {
				if(MAP[y][x]) continue;
				dp[y][x][0] += dp[y][x - 1][0];
				dp[y][x][0] += dp[y][x - 1][1];
				
				if(y > 0) {
					dp[y][x][2] += dp[y - 1][x][1];
					dp[y][x][2] += dp[y - 1][x][2];
					
					if(MAP[y - 1][x] || MAP[y][x - 1]) continue;
					
					dp[y][x][1] += dp[y - 1][x - 1][0];
					dp[y][x][1] += dp[y - 1][x - 1][1];
					dp[y][x][1] += dp[y - 1][x - 1][2];
				}
			}
		}
	}
	
	int N;
	boolean[][] MAP;
	long[][][] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		MAP = new boolean[N][N];
		dp = new long[N][N][3];
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < N ; x ++) {
				boolean value = lineInput.nextToken().charAt(0) == '1';
				MAP[y][x] = value;
			}
		}
		
		dp[0][1][0] = 1;
		
		functionIterator();
		
		long result = 0;
		for(int i = 0 ; i < 3 ; i ++) {
			result += dp[N - 1][N - 1][i];
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
