package com.kdedevelop.pdf.dp1.P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int[] function(int number) {
		if(dp[number] == null) {
			int[] prev = function(number - 1);
			
			int yello = prev[0] + prev[1];
			int blue = prev[0];
			
			dp[number] = new int[] {yello, blue};
		}
		
		return dp[number];
	}
	
	int N;
	int[][] dp;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][];
		
		dp[1] = new int[] {1, 1};
		
		int[] res = function(N);
		
		int result = res[0] + res[1];
		
		bw.write(result + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
