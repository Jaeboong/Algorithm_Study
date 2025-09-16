package com.kdedevelop.acmicpc.P16933;

import java.io.*;
import java.util.*;

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
	
	public boolean isNight(int day) {
		return day % 2 == 0;
	}
	
	public int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][][] visit = new boolean[N][M][K + 1];
		
		queue.offer(new int[] {0, 0, 0});
		visit[0][0][0] = true;
		
		int depth = 1;
		
		while(true) { 
			if(queue.isEmpty()) break;
			
			int size = queue.size();
			for(int i = 0 ; i < size ; i ++) {
				int[] curr = queue.poll();
				
				int x = curr[0];
				int y = curr[1];
				int count = curr[2];
				
				if(x == M - 1 && y == N - 1) return depth;
				
				for(int dir = 0 ; dir < 4 ; dir ++) {
					int nextX = x + dirX[dir];
					int nextY = y + dirY[dir];
					int nextCount = count;
					
					if(isOutOfMap(nextX, nextY)) continue;
					if(MAP[nextY][nextX]) {
						if(nextCount == K) continue;
						
						if(isNight(depth)) {
							queue.offer(new int[] {x, y, count});
							continue;
						} else {
							++ nextCount;
						}
					}
					if(visit[nextY][nextX][nextCount]) continue;
					
					queue.offer(new int[] {nextX, nextY, nextCount});
					visit[nextY][nextX][nextCount] = true;
				}
			}
			
			++ depth;
		}
		
		return -1;
	}
	
	int N, M, K;
	boolean[][] MAP;
	public void solution() throws IOException {
		StringTokenizer inputNMK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNMK.nextToken());
		M = Integer.parseInt(inputNMK.nextToken());
		K = Integer.parseInt(inputNMK.nextToken());
		MAP = new boolean[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String line = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				boolean value = line.charAt(j) == '1';
				MAP[i][j] = value;
			}
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
