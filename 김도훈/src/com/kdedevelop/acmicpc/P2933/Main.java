package com.kdedevelop.acmicpc.P2933;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) { 
		if(x < 0 || x >= C) return true;
		if(y < 0 || y >= R) return true;
		return false;
	}
	
	public void findClustor(int x, int y) {
		boolean[][] visit = new boolean[R][C];
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		visit[y][x] = true;
		
		List<int[]> clustor = new ArrayList<>();
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			
			clustor.add(curr);
			if(maxX < curr[0]) maxX = curr[0];
			if(minX > curr[0]) minX = curr[0];
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = curr[0] + dirX[dir];
				int nextY = curr[1] + dirY[dir];
				
				if(isOutOfMap(nextX, nextY)) continue;
				if(visit[nextY][nextX]) continue;
				if(!MAP[nextY][nextX]) continue;
				
				queue.offer(new int[] {nextX, nextY});
				visit[nextY][nextX] = true;
			}
		}
		
		for(int[] shard : clustor) {
			int currX = shard[0];
			int currY = shard[1];
			
			MAP[currY][currX] = false;
		}
		
		int moveCount = R;
		for(int[] shard : clustor) {
			int currX = shard[0];
			int currY = shard[1];
			
			int count = 0;
			for(int nextY = currY + 1 ; nextY < R ; nextY ++) {
				if(MAP[nextY][currX]) break;
				++ count;
			}
			
			moveCount = Math.min(count, moveCount);
		}
		
		for(int[] shard : clustor) {
			int currX = shard[0];
			int currY = shard[1];
			
			MAP[currY + moveCount][currX] = true;
		}
		
	}
	
	public void printBooleanMap(boolean[][] map) {
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				System.out.print((map[y][x] ? "x" : "."));
			}
			System.out.println("");
		}
		System.out.println("=============");
	}
	
	int R;
	int C;
	boolean[][] MAP;
	int N;
	int[] HEIGHTs;
	public void solution() throws IOException {
		StringTokenizer inputRC = new StringTokenizer(br.readLine());
		R = Integer.parseInt(inputRC.nextToken());
		C = Integer.parseInt(inputRC.nextToken());
		MAP = new boolean[R][C];
		for(int y = 0 ; y < R ; y ++) {
			String line = br.readLine();
			for(int x = 0 ; x < C ; x ++) {
				boolean mineral = line.charAt(x) == 'x';
				MAP[y][x] = mineral;
			}
		}
		
//		printBooleanMap(MAP);
		
		N = Integer.parseInt(br.readLine());
		HEIGHTs = new int[N];
		StringTokenizer inputHeight = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int height = Integer.parseInt(inputHeight.nextToken());
			HEIGHTs[i] = height;
		}
		
		//false == left || true == right
		boolean left = false;
		for(int height : HEIGHTs) {
			left = !left;
			
			int y = R - height;
			for(int i = 0 ; i < C ; i ++) {
				int x = left ? i : (C - 1) - i;
				
				if(MAP[y][x]) {
					MAP[y][x] = false;
					
//					System.out.println("HIT! : Y : " + y + " || X : " + x);
//					printBooleanMap(MAP);
					
					
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = x + dirX[dir];
						int nextY = y + dirY[dir];	
						
						if(isOutOfMap(nextX, nextY)) continue;
						if(!MAP[nextY][nextX]) continue;
						
						findClustor(nextX, nextY);
						
//						printBooleanMap(clustor);
					}
					
					break;
				}
			}
		}
		
//		System.out.println("RESULT");
//		printBooleanMap(MAP);
		
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				bw.write((MAP[y][x] ? "x" : "."));
			}
			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
