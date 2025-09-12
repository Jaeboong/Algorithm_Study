package com.kdedevelop.acmicpc.P13424;

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
					if(graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
//					if(graph[j][k] > graph[j][i] + graph[i][k]) {
//						graph[j][k] = graph[j][i] + graph[i][k];
//					}
				}
			}
		}
	}
	
	int TotalTestCase, N, M, K;
//	List<int[]>[] graph;
	int[][] graph;
	int[] Ks;
	public void solution() throws IOException {
		TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			
//			System.out.println("N : " + N + " || M : " + M);
			
			graph = new int[N][N];
			for(int i = 0 ; i < N ; i ++) {
				Arrays.fill(graph[i], Integer.MAX_VALUE >> 2);
				graph[i][i] = 0;
			}
			
//			for(int i = 0 ; i < N ; i ++) {
//				for(int j = 0 ; j < N ; j ++) {
//					System.out.print(String.format("%15d", graph[i][j]));
//				}
//				System.out.println("");
//			}
//			System.out.println("===============");
			
//			graph = new List[N];
//			for(int i = 0 ; i < N ; i ++) {
//				graph[i] = new ArrayList<>();
//			}
			for(int i = 0 ; i < M ; i ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(lineInput.nextToken()) - 1;
				int v = Integer.parseInt(lineInput.nextToken()) - 1;
				int w = Integer.parseInt(lineInput.nextToken());
					
				graph[u][v] = w;
				graph[v][u] = w;
				
//				graph[u].add(new int[] {v, w});
//				graph[v].add(new int[] {u, w});
			}
			
			K = Integer.parseInt(br.readLine());
			Ks = new int[K];
			StringTokenizer inputRoom = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < K ; i ++) {
				int value = Integer.parseInt(inputRoom.nextToken()) - 1;
				Ks[i] = value;
			}
			
			floydWarshall();
			
//			for(int i = 0 ; i < N ; i ++) {
//				for(int j = 0 ; j < N ; j ++) {
//					System.out.print(String.format("%5d", graph[i][j]));
//				}
//				System.out.println("");
//			}
//			System.out.println("===============");
			
			int result = -1;
			int minDist = Integer.MAX_VALUE;
			for(int i = 0 ; i < N ; i ++) {
				int sum = 0;
				for(int friend : Ks) {
					sum += graph[friend][i];
				}
				if(minDist > sum) {
					minDist = sum;
					result = i;
				}
			}
			
			bw.write(String.valueOf(result + 1));
			bw.write("\n");
			
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
