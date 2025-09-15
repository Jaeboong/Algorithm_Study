package com.kdedevelop.acmicpc.P2606;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int bfs() {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N];
		queue.offer(0);
		visit[0] = true;
		int count = 0;
		
		while(true) { 
			if(queue.isEmpty()) break;
			
			int curr = queue.poll();
			
			for(int next : graph[curr]) {
				if(visit[next]) continue;
				
				queue.offer(next);
				visit[next] = true;
				++ count;
			}
		}
		
		return count;
	}
	
	int N, M;
	List<Integer>[] graph;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new List[N];
		for(int i = 0 ; i < N ; i ++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		int result = bfs();
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
