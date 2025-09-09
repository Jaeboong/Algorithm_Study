package com.kdedevelop.acmicpc.P2133;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int function(int number) {
		if(number % 2 == 1) return 0;
		if(dp[number] == -1) {
//			int prev = function(number - 2);
//			dp[number] = new int[] {prev[0] * 4, prev[1] * 4, prev[2] * 3};
			dp[number] = function(number - 2) * 3;
//			System.out.println(dp[number]);
			for(int i = number - 4 ; i >= 0 ; i -= 2) {
//				System.out.println("I : " + i);
				dp[number] += function(i) * 2;
			}
		}
		
		return dp[number];
	}
	
	int N;
	int[] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[Math.max(3, N + 2)];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		dp[1] = 0;
		dp[2] = 3;
		
		int result = function(N);
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
