package com.kdedevelop.acmicpc.P1600;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	int[] dirKniteX = {-2, -2, -1, -1, 1, 1, 2, 2};
	int[] dirKniteY = {-1, 1, -2, 2, -2, 2, -1, 1};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= W) return true;
		if(y < 0 || y >= H) return true;
		return false;
	}
	
	public void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0, 0, 0});
		visit[0][0][0] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int time = curr[2];
			int kniteCount = curr[3];
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				int nextTime = time + 1;
				int nextKniteCount = kniteCount;
				
				if(isOutOfMap(nextX, nextY)) continue;
				if(MAP[nextY][nextX]) continue;
				if(visit[nextKniteCount][nextY][nextX] <= nextTime) continue;
				
				queue.offer(new int[] {nextX, nextY, nextTime, nextKniteCount});
				visit[nextKniteCount][nextY][nextX] = nextTime;
			}
			
			if(kniteCount < K) {
				for(int kniteDir = 0 ; kniteDir < 8 ; kniteDir ++) {
					int nextX = x + dirKniteX[kniteDir];
					int nextY = y + dirKniteY[kniteDir];
					int nextTime = time + 1;
					int nextKniteCount = kniteCount + 1;
					
					if(isOutOfMap(nextX, nextY)) continue;
					if(MAP[nextY][nextX]) continue;
					if(visit[nextKniteCount][nextY][nextX] <= nextTime) continue;
					
					queue.offer(new int[] {nextX, nextY, nextTime, nextKniteCount});
					visit[nextKniteCount][nextY][nextX] = nextTime;
				}
			}
		}
	}
	
	public void printVisit() {
		for(int i = 0 ; i <= K ; i ++) {
			for(int y = 0 ; y < H ; y ++) {
				for(int x = 0 ; x < W ; x ++) {
					System.out.print(String.format("%3d", (visit[i][y][x] == Integer.MAX_VALUE ? -1 : visit[i][y][x])));
				}
				System.out.println("");
			}
			System.out.println("=====");
		}
		System.out.println("=================================");
	}
	
	int K, W, H;
	boolean[][] MAP;
	int[][][] visit;
	public void solution() throws IOException {
		K = Integer.parseInt(br.readLine());
		StringTokenizer inputWH = new StringTokenizer(br.readLine());
		W = Integer.parseInt(inputWH.nextToken());
		H = Integer.parseInt(inputWH.nextToken());
		MAP = new boolean[H][W];
		visit = new int[K + 1][H][W];
		for(int y = 0 ; y < H ; y ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < W ; x ++) {
				boolean value = inputLine.nextToken().charAt(0) == '1';
				MAP[y][x] = value;
				for(int i = 0 ; i <= K ; i ++) {
					visit[i][y][x] = Integer.MAX_VALUE;
				}
			}
		}
		
		bfs();
		
//		printVisit();
		
		int result = Integer.MAX_VALUE;
		for(int i = 0 ; i <= K ; i ++) {
			result = Math.min(result, visit[i][H - 1][W - 1]);
		}
		
		bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
