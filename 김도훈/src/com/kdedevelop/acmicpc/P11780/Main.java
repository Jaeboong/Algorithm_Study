package com.kdedevelop.acmicpc.P11780;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void floydWarshall() {
		for(int k = 0 ; k < N ; k ++) {
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j ++) {
					if(graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
						
						
						path[i][j].clear();
						for(int temp : path[i][k]) {
							path[i][j].add(temp);
						}
						path[i][j].remove(path[i][j].size() - 1);
						for(int temp : path[k][j]) {
							path[i][j].add(temp);
						}
					}
				}
			}
		}
	}
	
	StringBuilder sb;
	long result;
	int N, M;
	long[][] graph;
	List<Integer>[][] path;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new long[N][N];
		path = new List[N][N];
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				path[i][j] = new ArrayList<>();
				if(i == j) {
					graph[i][j] = 0;
				} else {
					graph[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			int w = Integer.parseInt(inputLine.nextToken());
			if(path[u][v].size() == 0) {
				path[u][v].add(u);
				path[u][v].add(v);
			}
			graph[u][v] = Math.min(w, graph[u][v]);
		}
		
		floydWarshall();
		
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < N ; x ++) {
				sb.append(graph[y][x] == Integer.MAX_VALUE ? 0 : graph[y][x]).append(" ");
			}
			sb.append("\n");
		}
		
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j ++) {
				List<Integer> cPath = path[i][j];
				sb.append(cPath.size()).append(" ");
				for(int temp : cPath) {
					sb.append((temp + 1)).append(" ");
				}
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
