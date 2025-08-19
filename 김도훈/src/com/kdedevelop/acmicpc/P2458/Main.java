package com.kdedevelop.acmicpc.P2458;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		boolean[][] graph = new boolean[N][N];
		
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer input = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(input.nextToken())-1;
			int b = Integer.parseInt(input.nextToken())-1;
			graph[a][b] = true;
		}
		
		for (int k = 0 ; k < N ; k ++) {
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if (graph[i][k] && graph[k][j]) {
						graph[i][j] = true;
					}
				}
			}
		}
		
		for (int k = 0 ; k < N ; k ++) {
			for (int i = 0 ; i < N ; i ++) {
				for (int j = 0 ; j < N ; j ++) {
					if (graph[i][k] && graph[k][j]) {
						graph[i][j] = true;
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 0 ; i < N ; i ++) {
			int counter = 0;
			for (int j = 0 ; j < N ; j ++) {
				if (i == j) continue;
				if (graph[i][j] || graph[j][i]) {
					counter ++;
				}
			}
			if (counter == N-1) result ++;
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
