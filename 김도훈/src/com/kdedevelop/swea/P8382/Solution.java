package com.kdedevelop.swea.P8382;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	int[][] rowColDir = {{0, 1}, {2, 3}};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int bfs() {
		boolean[][][] visit = new boolean[N][N][2];
		Queue<int[]> queue = new LinkedList<>();
		visit[START[1]][START[0]][0] = true;
		queue.add(new int[] {START[0], START[1], 0});
		visit[START[1]][START[0]][1] = true;
		queue.add(new int[] {START[0], START[1], 1});
		
		int depth = 0;
		while(true) {
			if(queue.isEmpty()) break;
			
			int size = queue.size();
			
			for(int i = 0 ; i < size ; i ++) {
				int[] curr = queue.poll();
				
				if(curr[0] == END[0] && curr[1] == END[1]) return depth;
				
				for(int dir : rowColDir[curr[2]]) {
					int nextX = curr[0] + dirX[dir];
					int nextY = curr[1] + dirY[dir];
					int nextDir = curr[2] == 0 ? 1 : 0;
					
					if(isOutOfMap(nextX, nextY)) continue;
					if(visit[nextY][nextX][nextDir]) continue;
					
					visit[nextY][nextX][nextDir] = true;
					queue.offer(new int[] {nextX, nextY, nextDir});
				}
			}
			++ depth;
		}
		return -1;
	}
	
	long result;
	int N;
	int[] START;
	int[] END;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputStartEnd = new StringTokenizer(br.readLine().trim());
			
			int x1 = Integer.parseInt(inputStartEnd.nextToken());
			int y1 = Integer.parseInt(inputStartEnd.nextToken());
			int x2 = Integer.parseInt(inputStartEnd.nextToken());
			int y2 = Integer.parseInt(inputStartEnd.nextToken());
			
			int minX = Math.min(x1, x2);
			int minY = Math.min(y1, y2);
			
			x1 -= minX;
			x2 -= minX;
			y1 -= minY;
			y2 -= minY;
			
			START = new int[] {x1, y1};
			END = new int[] {x2, y2};
			
//			System.out.println("X1 : " + x1 + " || Y1 : " + y1 + " || X2 : " + x2 + " || Y2 : " + y2);
			
			int xDist = x1 - x2;
			if(xDist < 0) xDist *= -1;
			int yDist = y1 - y2;
			if(yDist < 0) yDist *= -1;
			
			N = Math.max(yDist, xDist) + 1;
			
			result = bfs();
			
			bw.write("#" + (testCase + 1) + " " + result + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
