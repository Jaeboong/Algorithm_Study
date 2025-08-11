package com.kdedevelop.acmicpc.P1167;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int maxNode = 0;
	static int maxDistance = 0;
	static void dfs(Map<Integer, Integer>[] graph, int N, boolean[] visited, int curr, int sumDistance) {
		if (visited[curr]) return;
		visited[curr] = true;
		if (maxDistance < sumDistance) {
			maxDistance = sumDistance;
			maxNode = curr;
		}
		Map<Integer, Integer> nodes = graph[curr];
		for (int to : nodes.keySet()) {
			if (visited[to]) continue;
			dfs(graph, N, visited, to, sumDistance+nodes.get(to));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer>[] graph = new HashMap[N];
		for (int i = 0 ; i < N ; i ++) graph[i] = new HashMap<>();
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken())-1;
			
			while (true) {
				int to = Integer.parseInt(st.nextToken())-1;
				if (to == -2) break;
				int distance = Integer.parseInt(st.nextToken());
				
				graph[from].put(to, distance);
				graph[to].put(from, distance);
			}
		}
		
		dfs(graph, N, new boolean[N], 0, 0);
		
		//System.out.println("MAX NODE : " + maxNode);
		//System.out.println("MAX DISTANCE : " + maxDistance);
		
		dfs(graph, N, new boolean[N], maxNode, 0);
		
		//System.out.println("MAX NODE : " + maxNode);
		//System.out.println("MAX DISTANCE : " + maxDistance);
		
		bw.write(String.valueOf(maxDistance));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
