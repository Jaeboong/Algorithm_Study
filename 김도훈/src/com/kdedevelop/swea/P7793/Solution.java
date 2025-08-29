package com.kdedevelop.swea.P7793;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public char[][] copyMap(char[][] map) {
		char[][] newMap = new char[N][M];
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				newMap[y][x] = map[y][x];
			}
		}
		return newMap;
	}
	
	public char[][] devil(char[][] map) {
		char[][] newMap = copyMap(map);
		
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				if(map[y][x] == '*') {
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = x + dirX[dir];
						int nextY = y + dirY[dir];
						
						if(isOutOfMap(nextX, nextY)) continue;
						
						char value = map[nextY][nextX];
						
						if(value == 'D' || value == 'X') continue;
						
						newMap[nextY][nextX] = '*';
					}
				}
			}
		}
		
		return newMap;
	}
	
	Map<Integer, char[][]> timeMap;
	public int bfs() {
		boolean[][] visit = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {START[0], START[1], 0});
		visit[START[1]][START[0]] = true;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int depth = curr[2];
			char[][] map = timeMap.get(depth);
			
			if(x == TARGET[0] && y == TARGET[1]) return depth;
//			if(map[y][x] == '*') return -1;
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				int nextDepth = depth + 1;
				
				if(isOutOfMap(nextX, nextY)) continue;
				if(map[nextY][nextX] == 'X') continue;
				if(map[nextY][nextX] == '*') continue;
				if(visit[nextY][nextX]) continue;
				
				queue.offer(new int[] {nextX, nextY, nextDepth});
				visit[nextY][nextX] = true;
				
				if(!timeMap.containsKey(nextDepth)) timeMap.put(nextDepth, devil(map));
			}
		}
		
		return -1;
	}
	
	long result;
	int N;
	int M;
	char[][] MAP;
	int[] START;
	int[] TARGET;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			MAP = new char[N][M];
			timeMap = new HashMap<>();
			for(int y = 0 ; y < N ; y ++) {
				String line = br.readLine();
				for(int x = 0 ; x < M ; x ++) {
					char value = line.charAt(x);
					if(value == 'S') {
						START = new int[] {x, y};
						value = '.';
					}
					if(value == 'D') TARGET = new int[] {x, y};
					MAP[y][x] = value;
				}
			}
//			timeMap.put(0, MAP);
			timeMap.put(0, devil(MAP));
			
			result = bfs();
			
			bw.write("#" + (testCase + 1) + " " + (result == -1 ? "GAME OVER" : result) + "\n");
		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
