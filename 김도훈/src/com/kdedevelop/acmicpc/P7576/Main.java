package com.kdedevelop.acmicpc.P7576;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	public Point(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
	int x;
	int y;
	int day;
}

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {-1, 1, 0, 0};
	int[] dirY = {0, 0, -1, 1};
	
	public boolean isOutOfBox(int x, int y) {
		if (x < 0 || x >= M) return true;
		if (y < 0 || y >= N) return true;
		return false;
	}
	
	int M;
	int N;
	int[][] BOX;
	public void solution() throws IOException {
		StringTokenizer inputMN = new StringTokenizer(br.readLine());
		M = Integer.parseInt(inputMN.nextToken());
		N = Integer.parseInt(inputMN.nextToken());
		int remain = N * M;
		BOX = new int[N][M];
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M ; j ++) {
				int tomato = Integer.parseInt(inputLine.nextToken());
				BOX[i][j] = tomato;
				if (tomato == 1) queue.add(new Point(j, i, 0));
				if (tomato == -1) remain --;
			}
		}
		
		int maxDay = 0;
		boolean[][] visited = new boolean[N][M];
		while (true) {
			if (queue.isEmpty()) break;
			
			Point tomato = queue.poll();
			if (visited[tomato.y][tomato.x]) continue;
			visited[tomato.y][tomato.x] = true;
			remain --;
			maxDay = Math.max(maxDay, tomato.day);
			
//			System.out.println("TOMATO X : " + tomato.x + " || TOMATO Y : " + tomato.y); 
			
			for (int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = tomato.x + dirX[dir];
				int nextY = tomato.y + dirY[dir];
				
				if (isOutOfBox(nextX, nextY)) continue;
				if (visited[nextY][nextX]) continue;
				if (BOX[nextY][nextX] == -1) continue;
				
				queue.add(new Point(nextX, nextY, tomato.day + 1));
			}
		}
		
		int result = remain == 0 ? maxDay : -1;
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
