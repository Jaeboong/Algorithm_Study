package com.kdedevelop.acmicpc.P13023;

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
	
//	public boolean isWork() {
//		for(int i = 0 ; i < N-1 ; i ++) {
//			if(!graph[i].contains(i + 1)) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	boolean[] visit;
	boolean result;
	public void isWork(int depth, int curr) {
//		System.out.println("DEPTH : " + depth + " || CURR : " + curr);
		if(depth == 4) {
			result = true;
		} else {
			visit[curr] = true;
			for(int next : graph[curr]) { 
				if(visit[next]) continue;
				isWork(depth + 1, next);
			}
			visit[curr] = false;
		}
	}
	
	int N;
	int M;
	Set<Integer>[] graph;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		result = false;
		visit = new boolean[N];
		graph = new Set[N];
		for(int i = 0 ; i < N ; i ++) graph[i] = new HashSet<>();
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputAB = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(inputAB.nextToken());
			int b = Integer.parseInt(inputAB.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i = 0 ; i < N ; i ++) {
//			System.out.println("========" + i + "=========");
			if(result) break;
			isWork(0, i);
		}
		
		bw.write(result ? "1" : "0");
		
//		bw.write(isWork() ? "1" : "0");
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
