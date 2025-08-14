package com.kdedevelop.swea.P1486;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void dfs(int depth, boolean[] used) {
		if (depth == N) {
			int sumOfHeight = 0;
			for (int i = 0 ; i < N ; i ++) {
				if (used[i]) sumOfHeight += heights[i];
			}
			if (sumOfHeight < B) return;
			result = Math.min(result, sumOfHeight-B);
		} else {
			used[depth] = true;
			dfs(depth+1, used);
			used[depth] = false;
			dfs(depth+1, used);
		}
	}
	
	static int result;
	static int N;
	static int B;
	static int[] heights;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Integer.MAX_VALUE;
			StringTokenizer inputNB = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNB.nextToken());
			B = Integer.parseInt(inputNB.nextToken());
			heights = new int[N];
			StringTokenizer inputHeight = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N ; i ++) heights[i] = Integer.parseInt(inputHeight.nextToken());
			
			dfs(0, new boolean[N]);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
