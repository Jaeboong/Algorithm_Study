package com.kdedevelop.acmicpc.P1948;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	long maxWeight;
	public void topology(int depth, int currentCityNumber, int sumOfDistance) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int curr = queue.poll();
			
			for(int[] edge : NODEs[curr]) {
				int next = edge[0];
				int weight = edge[1];
				
				-- inDegrees[next];
				if(inDegrees[next] == 0) queue.offer(next);
				
				dist[next] = Math.max(dist[next], dist[curr] + weight);
			}
		}
		maxWeight = dist[N - 1];
	}
	
	int maxWeightCount;
	public void bfs() {
		boolean[] visit = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N - 1);
		visit[N - 1] = true;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int curr = queue.poll();
			
			for(int[] edge : REV_NODEs[curr]) {
				int next = edge[0];
				int weight = edge[1];
				
				if(dist[next] + weight == dist[curr]) {
					++ maxWeightCount;
					if(visit[next]) continue;
					queue.offer(next);
					visit[next] = true;
				}
			}
		}
	}
	
	int N;
	int M;
	int[][] EDGEs;
	Set<int[]>[] NODEs;
	Set<int[]>[] REV_NODEs;
	int[] dist;
	int[] inDegrees;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N];
		EDGEs = new int[M][];
		
		NODEs = new Set[N];
		REV_NODEs = new Set[N];
		inDegrees = new int[N];
		
		maxWeight = 0;
		maxWeightCount = 0;
		
		for(int i = 0 ; i < N ; i ++) {
			NODEs[i] = new HashSet<>();
			REV_NODEs[i] = new HashSet<>();
//			NODEs[i] = new TreeSet<>((e1, e2) -> Integer.compare(e1[1], e2[1]));
		}
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(inputLine.nextToken()) - 1;
			int end = Integer.parseInt(inputLine.nextToken()) - 1;
			int weight = Integer.parseInt(inputLine.nextToken());
			++ inDegrees[end];
			NODEs[start].add(new int[] {end, weight});
			REV_NODEs[end].add(new int[] {start, weight});
		}
		
//		System.out.println(Arrays.toString(inDegrees));
		
		topology(0, 0, 0);
		
		bfs();
		
		bw.write(String.valueOf(maxWeight) + "\n" + String.valueOf(maxWeightCount));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
