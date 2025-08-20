package com.kdedevelop.acmicpc.P2644;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
	public Info(int number, int count) {
		this.number = number;
		this.count = count;
	}
	int number;
	int count;
}

public class Main {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N;
	int A;
	int B;
	int M;
	List<Integer>[] graph;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		graph = new List[N];
		for (int i = 0 ; i < N ; i ++) graph[i] = new LinkedList<>();
		StringTokenizer inputAB = new StringTokenizer(br.readLine());
		A = Integer.parseInt(inputAB.nextToken())-1;
		B = Integer.parseInt(inputAB.nextToken())-1;
		M = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(inputLine.nextToken())-1;
			int child = Integer.parseInt(inputLine.nextToken())-1;
			
			graph[parent].add(child);
			graph[child].add(parent);
		}
		
		int result = -1;
		boolean[] visited = new boolean[N];
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(A, 0));
		while (true) {
			if (queue.isEmpty()) break;
			
			Info curr = queue.poll();
			
			if (visited[curr.number]) continue;
			visited[curr.number] = true;
			
			if (curr.number == B) {
				result = curr.count;
				break;
			}
			
			for (int next : graph[curr.number]) {
				if (visited[next]) continue;
				queue.add(new Info(next, curr.count + 1));
			}
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
