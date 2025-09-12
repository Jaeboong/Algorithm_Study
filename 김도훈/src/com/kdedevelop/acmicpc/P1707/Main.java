package com.kdedevelop.acmicpc.P1707;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean bfs(int vertex) {
		int currentGroup = 0;
		int nextGroup = 1;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(vertex);
		visit[vertex] = true;
		group[currentGroup].add(vertex);
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int size = queue.size();
			for(int i = 0 ; i < size ; i ++) {
				
				int curr = queue.poll();
				
				for(int next : VERTEXs[curr]) {
					if(group[currentGroup].contains(next)) return false;
					if(visit[next]) continue;
					queue.offer(next);
					visit[next] = true;
					group[nextGroup].add(next);
				}
			}
			
			int temp = currentGroup;
			currentGroup = nextGroup;
			nextGroup = temp;
		}
		
		return true;
	}
	
	int V;
	int E;
	List<Integer>[] VERTEXs;
	boolean[] visit;
	Set<Integer>[] group;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputVE = new StringTokenizer(br.readLine());
			V = Integer.parseInt(inputVE.nextToken());
			E = Integer.parseInt(inputVE.nextToken());
			VERTEXs = new List[V];
			for(int i = 0 ; i < V ; i ++) {
				VERTEXs[i] = new ArrayList<>();
			}
			visit = new boolean[V];
			group = new Set[2];
			group[0] = new HashSet<>();
			group[1] = new HashSet<>();
			for(int i = 0 ; i < E ; i ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(lineInput.nextToken()) - 1;
				int v = Integer.parseInt(lineInput.nextToken()) - 1;
				
				VERTEXs[u].add(v);
				VERTEXs[v].add(u);
			}
			
			boolean result = true;
			for(int i = 0 ; i < V ; i ++) {
				if(visit[i]) continue;
				boolean temp = bfs(i);
				if(!temp) {
					result = false;
					break;
				}
			}
			
//			System.out.println("1 Size : " + group[0].size() + " || 2 Size : " + group[1].size());
//			System.out.println(Arrays.toString(group[0].toArray()));
//			System.out.println(Arrays.toString(group[1].toArray()));
			
			bw.write((result ? "YES" : "NO"));
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
