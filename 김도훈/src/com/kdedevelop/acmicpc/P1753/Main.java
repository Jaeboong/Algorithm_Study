package com.kdedevelop.acmicpc.P1753;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void dijkstra() {
		Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[1], e2[1]));
		distance[START] = 0;
		queue.offer(new int[] {START, 0});
		int count = 0;
		
		while(true) {
//			System.out.println("COUNT : " + count + " || SIZE : " + queue.size());
			if(queue.isEmpty()) break;
			if(count == V) break;
			
			int[] curr = queue.poll();
			
			if(visit[curr[0]]) continue;
			visit[curr[0]] = true;
			++ count;
			
			for(int[] next : VERTEX[curr[0]]) {
				if(visit[next[0]]) continue;
				
				int temp = curr[1] + next[1];
				if(distance[next[0]] > temp) {
					distance[next[0]] = temp;
					queue.offer(new int[] {next[0], temp});
				}
			}
		}
	}
	
	int V;
	int E;
	int START;
	List<int[]>[] VERTEX;
	int[] distance;
	boolean[] visit;
	public void solution() throws IOException {
		StringTokenizer inputVE = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(inputVE.nextToken());
		E = Integer.parseInt(inputVE.nextToken());
		START = Integer.parseInt(br.readLine().trim()) - 1;
		distance = new int[V];
		visit = new boolean[V];
		VERTEX = new List[V];
		for(int i = 0 ; i < V ; i ++) {
			VERTEX[i] = new ArrayList<>();
			distance[i] = Integer.MAX_VALUE;
		}
		for(int i = 0 ; i < E ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine().trim());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			int w = Integer.parseInt(inputLine.nextToken());
			
			VERTEX[u].add(new int[] {v, w});
		}
		
		dijkstra();
		
		for(int i = 0 ; i < V ; i ++) {
			bw.write((distance[i] == Integer.MAX_VALUE ? "INF" : String.valueOf(distance[i])) + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
