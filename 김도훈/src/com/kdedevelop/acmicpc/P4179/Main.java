package com.kdedevelop.acmicpc.P4179;


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
	
	public boolean isOutOfMage(int x, int y) {
		if(x < 0 || x >= C) return true;
		if(y < 0 || y >= R) return true;
		return false;
	}
	
	public void fire() {
		int size = FIREs.size();
		for(int i = 0 ; i < size ; i ++) {
			int[] curr = FIREs.poll();
			int x = curr[0];
			int y = curr[1];
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				
				if(isOutOfMage(nextX, nextY)) continue;
				if(MAGE[nextY][nextX] == '#') continue;
				if(MAGE[nextY][nextX] == 'F') continue;
				
				MAGE[nextY][nextX] = 'F';
				FIREs.offer(new int[] {nextX, nextY});
			}
		}
	}
	
	public int bfs() {
		boolean[][] visit = new boolean[R][C];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {START[0], START[1]});
		visit[START[1]][START[0]] = true;
		
		int depth = 0;
		while(true) {
			if(queue.isEmpty()) break;
			
			int size = queue.size();
//			System.out.println(size);
			fire();
			
			for(int i = 0 ; i < size ; i ++) {
				int[] curr = queue.poll();
				int x = curr[0];
				int y = curr[1];
				
				for(int dir = 0 ; dir < 4 ; dir ++) {
					int nextX = x + dirX[dir];
					int nextY = y + dirY[dir];
					
					if(isOutOfMage(nextX, nextY)) return depth;
					if(MAGE[nextY][nextX] == '#') continue;
					if(MAGE[nextY][nextX] == 'F') continue;
					if(visit[nextY][nextX]) continue;
					
					queue.offer(new int[] {nextX, nextY});
					visit[nextY][nextX] = true;
				}
			}
			
			++ depth;
		}
		
		return -1;
	}
	
	public void printMage() {
		System.out.println("=================");
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				System.out.print(MAGE[y][x]);
			}
			System.out.println("");
		}
		System.out.println("=================");
	}
	
	int R;
	int C;
	char[][] MAGE;
	int[] START;
	Queue<int[]> FIREs;
	public void solution() throws IOException {
		StringTokenizer inputRC = new StringTokenizer(br.readLine());
		R = Integer.parseInt(inputRC.nextToken());
		C = Integer.parseInt(inputRC.nextToken());
		FIREs = new LinkedList<>();
		MAGE = new char[R][C];
		for(int y = 0 ; y < R ; y ++) {
			String line = br.readLine();
			for(int x = 0 ; x < C ; x ++) {
				char value = line.charAt(x);
				if(value == 'J') {
					START = new int[] {x, y};
					value = '.';
				}
				if(value == 'F') {
					FIREs.add(new int[] {x, y});
				}
				MAGE[y][x] = value;
			}
		}
		
//		for(int i = 0 ; i < 3 ; i ++) {
//			printMage();
//			fire();
//		}
		
		
		int result = bfs();
		
		bw.write((result == -1 ? "IMPOSSIBLE" : String.valueOf(result + 1)));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}

