package com.kdedevelop.acmicpc.P13549;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean isOutOfMap(int x) {
		if(x < 0 || x >= field) return true;
		return false;
	}
	
	public void bfs() {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		queue.offer(new int[] {N, 0});
		visit[N] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int time = curr[1];
			if(visit[x] < time) continue;
			
			if(x == K) return;
//			System.out.println("X : " + x + " || TIME : " + time);
			
			int[][] nextArray = (x < K ? (x == 0 ? new int[][] {{x - 1, time + 1}, {x + 1, time + 1}} : new int[][] {{x * 2, time}, {x - 1, time + 1}, {x + 1, time + 1}} ) : new int[][] {{x - 1, time + 1}});
			
			for(int[] next : nextArray) {
				int nextX = next[0];
				int nextTime = next[1];
				
				if(isOutOfMap(nextX)) continue;
				if(visit[nextX] < nextTime) continue;
				
				visit[nextX] = nextTime;
				queue.offer(next);
			}
		}
	}
	
	int field = 100001;
	int N, K;
	int[] visit;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		visit = new int[field];
		Arrays.fill(visit, Integer.MAX_VALUE);
		
		bfs();
		
		int result = visit[K];
		
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");
		
		bw.write(sb.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
