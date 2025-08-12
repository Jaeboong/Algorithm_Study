package com.kdedevelop.acmicpc.P4803;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static boolean isTree(List<Integer>[] tree, int N, boolean[] visited, int root) {
		Deque<int[]> stack = new ArrayDeque<>();
		stack.add(new int[]{root, -1});
		
		while (true) {
			if (stack.isEmpty()) break;
			int[] curr = stack.pop();
			int node = curr[0];
			int parent = curr[1];
			if (visited[node]) return false;
			visited[node] = true;
			for (int next : tree[node]) {
				if (next == parent) continue;
				if (visited[next]) return false;
				stack.add(new int[] {next, node});
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int testCase = 0;
		while (true) {
			testCase ++;
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(inputNM.nextToken());
			int M = Integer.parseInt(inputNM.nextToken());
			
			if (N == 0 && M == 0) break;
			
			List<Integer>[] tree = new ArrayList[N];
			for (int i = 0 ; i < N ; i ++) {
				tree[i] = new ArrayList<>();
			}
			
			for (int i = 0 ; i < M ; i ++) {
				StringTokenizer inputEdge = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(inputEdge.nextToken())-1;
				int v = Integer.parseInt(inputEdge.nextToken())-1;
				tree[u].add(v);
				tree[v].add(u);
			}
			
			int result = 0;
			
			boolean[] visited = new boolean[N];
			for (int i = 0 ; i < N ; i ++) {
				if (visited[i]) continue;
				result += isTree(tree, N, visited, i) ? 1 : 0;
			}
			
			switch (result) {
				case 0: bw.write("Case " + testCase + ": No trees.");
					break;
				case 1: bw.write("Case "+ testCase + ": There is one tree.");
					break;
				default: bw.write("Case " + testCase + ": A forest of " + result + " trees.");
					break;
			}
			
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
