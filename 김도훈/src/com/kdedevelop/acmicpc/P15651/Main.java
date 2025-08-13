package com.kdedevelop.acmicpc.P15651;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	public static void dfs(int n, int r, int depth, int[] data) throws IOException {
		if (depth == r) {
			for (int i = 0 ; i < r ; i ++) {
				bw.write(String.valueOf(data[i]+1) + " ");
			}
			bw.write("\n");
		} else {
			for (int i = 0 ; i < n ; i ++) {
				data[depth] = i;
				dfs(n, r, depth+1, data);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		dfs(N, M, 0, new int[M]);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
