package com.kdedevelop.acmicpc.P11726;

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
			dp[number] = (function(number - 1) + function(number - 2)) % 10007;
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
		dp[1] = 1;
		dp[2] = 2;
		
//		for(int i = 3 ; i < 1000 ; i ++) {
//			System.out.println(i + " : " + function(i));
//		}
		
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
