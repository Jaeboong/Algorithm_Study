package com.kdedevelop.acmicpc.P17070;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 1, 1};
	int[] dirY = {1, 0, 1};
	int[] downPipe = {0};
	int[] rightPipe = {1};
	int[] rightDownPipe = {2, 0, 1};
	int[][] pipes = {rightPipe, rightDownPipe, downPipe};
	int[] nextPipe = {-1, 0, 1};
	
	public boolean isOutOfHouse(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public boolean isPipeAvailable(int x, int y, int[] pipe) {
		for(int dir : pipe) {
			int nextX = x + dirX[dir];
			int nextY = y + dirY[dir];
			
			if(isOutOfHouse(nextX, nextY)) return false;
			if(visit[nextY][nextX]) return false;
			if(HOUSE[nextY][nextX]) return false;
		}	
		return true;
	}
	
	public void updateVisit(int x, int y, int[] pipe, boolean value) {
		for(int dir : pipe) {
			int nextX = x + dirX[dir];
			int nextY = y + dirY[dir];
			visit[nextY][nextX] = value;
		}
	}
	
	public void printVisit() {
		System.out.println("=====");
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print((visit[i][j] ? "1" : "0") + " ");
			}
			System.out.println("");
		}
		System.out.println("=====");
	}
	
	boolean[][] visit;
	int count;
	public void dfs(int x, int y, int pipeType) {
//		System.out.println("X : " + x + " || Y : " + y + " || TYPE : " + pipeType);
//		printVisit();
		if(x == (N - 1) && y == (N - 1)) {
			++ count;
		} else {
			for(int temp : nextPipe) {
				int currentPipeType = pipeType + temp;
				if(currentPipeType < 0 || currentPipeType > 2) continue;
				int[] pipe = pipes[currentPipeType];

				if(!isPipeAvailable(x, y, pipe)) continue;

				updateVisit(x, y, pipe, true);
				
				dfs((x + dirX[pipe[0]]), (y + dirY[pipe[0]]), currentPipeType);
				
				updateVisit(x, y, pipe, false);
			}
		}
	}
	
	int N;
	boolean[][] HOUSE;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		HOUSE = new boolean[N][N];
		visit = new boolean[N][N];
		visit[0][0] = true;
		visit[0][1] = true;
		count = 0;
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < N ; x ++) {
				boolean wall = inputLine.nextToken().charAt(0) == '1';
				HOUSE[y][x] = wall;
			}
		}
		
		dfs(1, 0, 0);
		
		bw.write(String.valueOf(count));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
