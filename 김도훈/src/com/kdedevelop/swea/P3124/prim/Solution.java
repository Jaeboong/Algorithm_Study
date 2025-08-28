package com.kdedevelop.swea.P3124.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean put(int node) {
		if(join[node]) return false;
		join[node] = true;
		for(int[] edge : NODE[node]) {
			if(join[edge[0]]) continue;
			queue.offer(edge);
		}
		return true;
	}
	
	long result;
	int V;
	int E;
	boolean[] join;
	int[][] EDGEs;
	List<int[]>[] NODE;
	Queue<int[]> queue;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputVE = new StringTokenizer(br.readLine());
			V = Integer.parseInt(inputVE.nextToken());
			join = new boolean[V];
			E = Integer.parseInt(inputVE.nextToken());
			EDGEs = new int[E][];
			NODE = new List[V];
			queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[1], e2[1]));
			for(int i = 0 ; i < V ; i ++) NODE[i] = new ArrayList<>();
			for(int i = 0 ; i < E ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(inputLine.nextToken()) - 1;
				int v2 = Integer.parseInt(inputLine.nextToken()) - 1;
				int weight = Integer.parseInt(inputLine.nextToken());
				NODE[v1].add(new int[] {v2, weight});
				NODE[v2].add(new int[] {v1, weight});
				int[] edge = {v1, v2, weight};
				EDGEs[i] = edge;
			}
			
			put(0);
			int count = 1;
			long totalWeight = 0;
			while(true) {
				if(queue.isEmpty()) break;
				if(count == V) break;
				
				int[] node = queue.poll();
				boolean success = put(node[0]);
				if(success) {
					++ count;
					totalWeight += node[1];
				}
			}
			
			result = totalWeight;
			bw.write("#" + (testCase + 1) + " " + result + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
