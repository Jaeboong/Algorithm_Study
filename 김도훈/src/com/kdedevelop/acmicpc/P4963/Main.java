package com.kdedevelop.acmicpc.P4963;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
	
	int[] dirX = {-1, -1, 0, 1, 1, 1, 0, -1};
	int[] dirY = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public boolean isOutOfMap(Point point) {
		if (point.x < 0 || point.x >= W) return true;
		if (point.y < 0 || point.y >= H) return true;
		return false;
	}
	
	int W;
	int H;
	boolean[][] MAP;
	int result;
	public void solution() throws IOException {
		while (true) {
			result = 0;
			StringTokenizer inputWH = new StringTokenizer(br.readLine());
			W = Integer.parseInt(inputWH.nextToken());
			H = Integer.parseInt(inputWH.nextToken());
			
			if (W == 0 && H == 0) break;
			
			MAP = new boolean[H][W];
			for (int i = 0 ; i < H ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < W ; j ++) {
					MAP[i][j] = Integer.parseInt(inputLine.nextToken()) == 1 ? true : false;
				}
			}
			
			int result = 0;
			Queue<Point> queue = new LinkedList<>();
			boolean[][] visited = new boolean[H][W];
			for (int i = 0 ; i < H ; i ++) {
				for (int j = 0 ; j < W ; j ++) {
					if (visited[i][j]) continue;
					if (!MAP[i][j]) continue;
					result ++;
					queue.add(new Point(j, i));
					
					while (true) {
						if (queue.isEmpty()) break;
						
						Point curr = queue.poll();
						if (visited[curr.y][curr.x]) continue;
						visited[curr.y][curr.x] = true;
						
						for (int dir = 0 ; dir < 8 ; dir ++) {
							Point nextPoint = new Point(curr.x + dirX[dir], curr.y + dirY[dir]);
							
							if (isOutOfMap(nextPoint)) continue;
							if (visited[nextPoint.y][nextPoint.x]) continue;
							if (!MAP[nextPoint.y][nextPoint.x]) continue;
							
							queue.add(nextPoint);
						}
					}
				}
			}
			
			
			bw.write(String.valueOf(result) + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
