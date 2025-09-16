package com.kdedevelop.acmicpc.P1238;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int[] dijkstra(List<int[]>[] graph) {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		boolean[] visit = new boolean[N];
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		queue.offer(new int[] {X, 0});
		dist[X] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int location = curr[0];
			int sumOfDistance = curr[1];
			
			visit[location] = true;
			
			for(int[] next : graph[location]) {
				int nextLocation = next[0];
				int nextDistance = next[1];
				
				if(visit[nextLocation]) continue;
				
				int sumOfNextDistance = sumOfDistance + nextDistance;
				
				if(dist[nextLocation] > sumOfNextDistance) {
					queue.offer(new int[] {nextLocation, sumOfNextDistance});
					dist[nextLocation] = sumOfNextDistance;
				}
			}
		}
		return dist;
	}
	
	int N, M, X;
	List<int[]>[] goGraph;
	List<int[]>[] comeGraph;
	public void solution() throws IOException {
		StringTokenizer inputNMX = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNMX.nextToken());
		M = Integer.parseInt(inputNMX.nextToken());
		X = Integer.parseInt(inputNMX.nextToken()) - 1;
		
		goGraph = new List[N];
		comeGraph = new List[N];
		for(int i = 0 ; i < N ; i ++) {
			goGraph[i] = new ArrayList<>();
			comeGraph[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			int w = Integer.parseInt(inputLine.nextToken());
			
			goGraph[u].add(new int[] {v, w});
			comeGraph[v].add(new int[] {u, w});
		}
		
		int[] goDist = dijkstra(goGraph);
		int[] comeDist = dijkstra(comeGraph);
		
		int result = 0;
		for(int i = 0 ; i < N ; i ++) {
			result = Math.max(result, goDist[i] + comeDist[i]);
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
