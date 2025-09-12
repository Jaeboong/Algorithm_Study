package com.kdedevelop.acmicpc.P2294;

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
	
	public int functionRec(int value) {
		if(dp[value] == Integer.MAX_VALUE) {
			int result = Integer.MAX_VALUE;
			
			for(int coin : COINs) {
				if(value - coin < 0) continue;
				int temp = functionRec(value - coin);
				result = Math.min(result, temp);
			}
			
			dp[value] = result == Integer.MAX_VALUE ? -1 : result;
		}
		
		return dp[value];
	}
	
	public int functionIter(int value) {
		for(int i = 0 ; i <= value ; i ++) {
			if(dp[i] != Integer.MAX_VALUE) {
				for(int coin : COINs) {
					int target = i + coin;
					if(target > value) continue;
					int count = dp[i] + 1;
					dp[target] = Math.min(dp[target], count);
				}
			}
		}
		
		return dp[value] == Integer.MAX_VALUE ? -1 : dp[value];
	}
	
	int N, K;
	int[] COINs;
//	Set<Integer> COINs;
	int[] dp;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		COINs = new int[N];
//		COINs = new HashSet<>();
		dp = new int[K + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 0 ; i < N ; i ++) {
			int value = Integer.parseInt(br.readLine());
//			COINs.add(value);
			COINs[i] = value;
			if(value > K) continue;
			dp[value] = 1;
		}
		
//		System.out.println(Arrays.toString(dp));
		
//		int result = functionRec(K);
		int result = functionIter(K);
		
//		System.out.println(Arrays.toString(dp));
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
