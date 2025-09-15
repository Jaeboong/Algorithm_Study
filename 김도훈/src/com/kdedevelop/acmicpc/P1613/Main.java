package com.kdedevelop.acmicpc.P1613;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void floydWarshall() {
		for(int k = 0 ; k < N ; k ++) {
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j ++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
	}
	
	int N, K, S;
//	List<Integer>[] graph;
	int[][] graph;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		graph = new int[N][N];
//		graph = new List[N];
		for(int i = 0 ; i < N ; i ++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE / 2);
			graph[i][i] = 0;
		}
		for(int i = 0 ; i < K ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			
			graph[u][v] = 1;
//			graph[u].add(v);
		}
		
		floydWarshall();
		
//		System.out.println("");
//		for(int i = 0 ; i < N ; i ++) {
//			for(int j = 0 ; j < N ; j ++) {
//				System.out.print(String.format("%3d", (graph[i][j] == Integer.MAX_VALUE / 2 ? -1 : graph[i][j])));
//			}
//			System.out.println("");
//		}
//		System.out.println("============");
		
		S = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < S ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			
			if(graph[u][v] != Integer.MAX_VALUE / 2) {
				bw.write("-1\n");
				continue;
			}
			
			if(graph[v][u] != Integer.MAX_VALUE / 2) {
				bw.write("1\n");
				continue;
			}
			
			bw.write("0\n");
			
//			int count = 0;
//			for(int j = 0 ; j < N ; j ++) {
//				
//			}
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
