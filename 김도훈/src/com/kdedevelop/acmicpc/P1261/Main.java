package com.kdedevelop.acmicpc.P1261;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public void bfs() {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		queue.offer(new int[] {0, 0, 0});
		visit[0][0] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int time = curr[2];
			
			if(x == M - 1 && y == N - 1) break;
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				
				if(isOutOfMap(nextX, nextY)) continue;
				
				boolean isWall = MAP[nextY][nextX];
				int nextTime = time + (isWall ? 1 : 0);
				
				if(visit[nextY][nextX] <= nextTime) continue;
				
				queue.offer(new int[] {nextX, nextY, nextTime});
				visit[nextY][nextX] = nextTime;
			}
		}
	}
	
	int M, N;
	boolean[][] MAP;
	int[][] visit;
	public void solution() throws IOException {
		StringTokenizer inputMN = new StringTokenizer(br.readLine());
		M = Integer.parseInt(inputMN.nextToken());
		N = Integer.parseInt(inputMN.nextToken());
		MAP = new boolean[N][M];
		visit = new int[N][M];
		for(int y = 0 ; y < N ; y ++) {
			String line = br.readLine();
			for(int x = 0 ; x < M ; x ++) {
				boolean value = line.charAt(x) == '1';
				MAP[y][x] = value;
				visit[y][x] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		
		int result = visit[N - 1][M - 1];
		
		bw.write(result + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
