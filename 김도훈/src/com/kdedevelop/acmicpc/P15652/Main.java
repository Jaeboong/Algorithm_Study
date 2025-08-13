package com.kdedevelop.acmicpc.P15652;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static BufferedWriter bw;
	
	public static void dfs(int N, int M, int depth, int start, int[] data) throws IOException {
		if (depth == M) {
			for (int i = 0 ; i < M ; i ++) {
				bw.write(String.valueOf(data[i]+1) + " ");
			}
			bw.write("\n");
		} else {
			for (int i = start ; i < N ; i ++) {
				data[depth] = i;
				dfs(N, M, depth+1, i, data);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		dfs(N, M, 0, 0, new int[N]);
		
		bw.flush();
		bw.close();
		br.close();
	}
}
