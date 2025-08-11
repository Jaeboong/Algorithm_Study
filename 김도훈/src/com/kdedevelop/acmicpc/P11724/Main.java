package com.kdedevelop.acmicpc.P11724;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("Test3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		List<Integer>[] graph = new ArrayList[N];
		for (int i = 0 ; i < N ; i ++) {
			graph[i] = new ArrayList<>(N);
		}
		
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer input = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(input.nextToken())-1;
			int v = Integer.parseInt(input.nextToken())-1;
			graph[u].add(v);
			graph[v].add(u);
		}
		
		int result = 0;
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<>(N);
		for (int i = 0 ; i < N ; i ++) {
			if (visited[i]) continue;
			
			queue.add(i);
			result ++;
			while (true) {
				if (queue.isEmpty()) break;
				int n = queue.poll();
				if (visited[n]) continue;
				visited[n] = true;
				for (int node : graph[n]) {
					if (visited[node]) continue;
					queue.add(node);
				}
			}
		} 
		
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

}