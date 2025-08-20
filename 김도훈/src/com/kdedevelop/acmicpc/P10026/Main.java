package com.kdedevelop.acmicpc.P10026;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}

public class Main {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfArt(int x, int y) {
		if (x < 0 || x >= N) return true;
		if (y < 0 || y >= N) return true;
		return false;
	}
	
	public void bfs(boolean isEyeProblem, Point start) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		
		while (true) {
			if (queue.isEmpty()) break;
			
			Point curr = queue.poll();
			if (visited[curr.y][curr.x]) continue;
			visited[curr.y][curr.x] = true;
			
			char currentColor = isEyeProblem ? PROBLEM_ART[curr.y][curr.x] : ART[curr.y][curr.x];
//			System.out.println("BFS X : " + curr.x + " || Y : " + curr.y);
			
			for (int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = curr.x + dirX[dir];
				int nextY = curr.y + dirY[dir];
				
				if (isOutOfArt(nextX, nextY)) continue;
				if (visited[nextY][nextX]) continue;
				
				char nextColor = isEyeProblem ? PROBLEM_ART[nextY][nextX] : ART[nextY][nextX];
				
				if (currentColor == nextColor) {
					queue.add(new Point(nextX, nextY));
				}
			}
		}
	}
	
	int N;
	char[][] ART;
	char[][] PROBLEM_ART;
	boolean[][] visited;
	int result;
	int problemResult;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		ART = new char[N][N];
		PROBLEM_ART = new char[N][N];
		for (int i = 0 ; i < N ; i ++) {
			String line = br.readLine();
			for (int j = 0 ; j < N ; j ++) {
				ART[i][j] = line.charAt(j);
				PROBLEM_ART[i][j] = line.charAt(j);
				if (PROBLEM_ART[i][j] == 'G') PROBLEM_ART[i][j] = 'R';
			}
		}
		
		visited = new boolean[N][N];
		result = 0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				if (visited[i][j]) continue;
				bfs(false, new Point(j, i));
//				System.out.println("X : " + j + " || Y : " + i);
				result ++;
			}
		}
		
		visited = new boolean[N][N];
		problemResult = 0;
		for (int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < N ; j ++) {
				if (visited[i][j]) continue;
				bfs(true, new Point(j, i));
				problemResult ++;
			}
		}
		
		bw.write(String.valueOf(result) + " " + String.valueOf(problemResult));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
