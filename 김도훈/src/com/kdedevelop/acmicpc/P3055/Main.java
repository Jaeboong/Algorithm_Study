package com.kdedevelop.acmicpc.P3055;

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

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public char[][] copyBoard(char[][] board) {
		char[][] result = new char[R][C];
		
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				result[y][x] = board[y][x];
			}
		}
		
		return result;
	}
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= C) return true;
		if(y < 0 || y >= R) return true;
		return false;
	}
	
	public char[][] flood(char[][] board) {
		char[][] result = copyBoard(board);
		
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				
				if(board[y][x] == '*') {
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = x + dirX[dir];
						int nextY = y + dirY[dir];
						
						if(isOutOfMap(nextX, nextY)) continue;
						if(board[nextY][nextX] == 'D') continue;
						if(board[nextY][nextX] == 'X') continue;
						
						result[nextY][nextX] = '*';
					}
				}
				
			}
		}
		
		return result;
	}
	
	public int bfs() {
		boolean[][] visit = new boolean[R][C];
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
			
			if(x == END[0] && y == END[1]) return depth;
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				int nextDepth = depth + 1;
				
				if(isOutOfMap(nextX, nextY)) continue;
				if(visit[nextY][nextX]) continue;
				if(map[nextY][nextX] == '*') continue;
				if(map[nextY][nextX] == 'X') continue;
				
				queue.offer(new int[] {nextX, nextY, nextDepth});
				visit[nextY][nextX] = true;
				
				if(!timeMap.containsKey(nextDepth)) timeMap.put(nextDepth, flood(map));
			}
		}
		
		return -1;
	}
	
	public void printMap(char[][] MAP) {
		System.out.println("================");
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				System.out.print(MAP[y][x] + " ");
			}
			System.out.println("");
		}
		System.out.println("================");
	}
	
	int R;
	int C;
	char[][] MAP;
	int[] START;
	int[] END;
	Map<Integer, char[][]> timeMap;
	public void solution() throws IOException {
		StringTokenizer inputRC = new StringTokenizer(br.readLine());
		R = Integer.parseInt(inputRC.nextToken());
		C = Integer.parseInt(inputRC.nextToken());
		MAP = new char[R][C];
		timeMap = new HashMap<>();
		for(int y = 0 ; y < R ; y ++) {
			String line = br.readLine();
			for(int x = 0 ; x < C ; x ++) {
				char value = line.charAt(x);
				if(value == 'S') {
					START = new int[] {x, y};
					value = '.';
				}
				if(value == 'D') END = new int[] {x, y};
				MAP[y][x] = value;
			}
		}
		
		timeMap.put(0, flood(MAP));
		int result = bfs();
		
//		for(int depth : timeMap.keySet()) {
//			char[][] map = timeMap.get(depth);
//			printMap(map);
//		}
		
		bw.write((result == -1 ? "KAKTUS" : String.valueOf(result)));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
