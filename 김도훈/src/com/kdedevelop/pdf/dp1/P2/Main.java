package com.kdedevelop.pdf.dp1.P2;

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
		if(dp[number] == -1) {
			dp[number] = function(number - 1) * 2 + function(number - 2);
		}
		
		return dp[number];
	}
	
	int N;
	int[] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 2];
		Arrays.fill(dp, -1);
		
		dp[0] = 0;
		dp[1] = 2;
		dp[2] = 5;
		
		
		int result = function(N);
		
		bw.write(result + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
