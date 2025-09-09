package com.kdedevelop.acmicpc.P2206;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.MXBean;

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
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0, 1, 0});
		visit[0][0][0] = 1;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int time = curr[2];
			int isBreak = curr[3];
			
			if(x == M - 1 && y == N - 1) break;
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				int nextTime = time + 1;
				
				if(isOutOfMap(nextX, nextY)) continue;
//				if(visit[isBreak][nextY][nextX] <= nextTime) continue;
				
				boolean isWall = MAP[nextY][nextX];
				
				if(isWall) {
					if(isBreak == 1) {
						continue;
					} else {
						if(visit[1][nextY][nextX] <= nextTime) continue;
						queue.offer(new int[] {nextX, nextY, nextTime, 1});
						visit[1][nextY][nextX] = nextTime;
					}
				} else {
					if(visit[isBreak][nextY][nextX] <= nextTime) continue;
					queue.offer(new int[] {nextX, nextY, nextTime, isBreak});
					visit[isBreak][nextY][nextX] = nextTime;
				}
			}
			
		}
	}
	
	int N, M;
	boolean[][] MAP;
	int[][][] visit;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		MAP = new boolean[N][M];
		visit = new int[2][N][M];
		for(int y = 0 ; y < N ; y ++) {
			String line = br.readLine();
			for(int x = 0 ; x < M ; x ++) {
				boolean value = line.charAt(x) == '1';
				MAP[y][x] = value;
				visit[0][y][x] = Integer.MAX_VALUE;
				visit[1][y][x] = Integer.MAX_VALUE;
			}
		}
		
		bfs();
		
		int result = Math.min(visit[0][N - 1][M - 1], visit[1][N - 1][M - 1]);
		
		bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
