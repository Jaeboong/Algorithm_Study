package com.kdedevelop.acmicpc.P2178;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	public Point(int x, int y, int moveCount) {
		this.x = x;
		this.y = y;
		this.moveCount = moveCount;
	}
	int x;
	int y;
	int moveCount;
}

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {-1, 1, 0, 0};
	int[] dirY = {0, 0, -1, 1};
	
	public boolean isOutOfMage(Point point) {
		if (point.x < 0 || point.x >= M) return true;
		if (point.y < 0 || point.y >= N) return true;
		return false;
	}
	
	int N;
	int M;
	boolean[][] MAGE;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		MAGE = new boolean[N][M];
		for (int i = 0 ; i < N ; i ++) {
			String line = br.readLine();
			for (int j = 0 ; j < M ; j ++) {
				if (line.charAt(j) == '1') MAGE[i][j] = true;
			}
		}
		
		int result = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 0));
		
		while (true) {
			if (queue.isEmpty()) break;
			
			Point point = queue.poll();
			if (visited[point.y][point.x]) continue;
			visited[point.y][point.x] = true;
			
			if (point.x == M-1 && point.y == N-1) {
				result = Math.min(result, point.moveCount);
				continue;
			}
			
			for (int dir = 0 ; dir < 4 ; dir ++) {
				Point nextPoint = new Point(point.x + dirX[dir], point.y + dirY[dir], point.moveCount + 1);
				if (isOutOfMage(nextPoint)) continue;
				if (!MAGE[nextPoint.y][nextPoint.x]) continue;
				if (visited[nextPoint.y][nextPoint.x]) continue;
				queue.add(nextPoint);
			}
		}
		
		bw.write(String.valueOf(result+1));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
