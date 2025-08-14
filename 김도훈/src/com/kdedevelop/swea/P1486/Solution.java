package com.kdedevelop.swea.P1486;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void dfs(int depth, int height) {
		if (height >= B) result = Math.min(result, (height-B));
		if (depth == N) return;
		
		dfs(depth+1, height + heights[depth]);
		dfs(depth+1, height);
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
			//System.out.println(Arrays.toString(heights));
			
			dfs(0, 0);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
