package com.kdedevelop.acmicpc.P11048;

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
	
	int[] dirX = {0, 1, 1};
	int[] dirY = {1, 0, 1};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public void bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {0, 0});
		dp[0][0] = MAP[0][0];
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			
			for(int dir = 0 ; dir < 3 ; dir ++) {
				int nextX = curr[0] + dirX[dir];
				int nextY = curr[1] + dirY[dir];
				
				if(isOutOfMap(nextX, nextY)) continue;
				
				int candy = MAP[nextY][nextX];
				if(dp[nextY][nextX] < dp[curr[1]][curr[0]] + candy) {
					queue.offer(new int[] {nextX, nextY});
					dp[nextY][nextX] = dp[curr[1]][curr[0]] + candy;
				}
			}
		}
	}
	
	public void functionIter() {
		dp[0][0] = MAP[0][0];
		
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				if(y > 0) dp[y][x] = Math.max(dp[y][x], dp[y - 1][x] + MAP[y][x]);
				if(x > 0) dp[y][x] = Math.max(dp[y][x], dp[y][x - 1] + MAP[y][x]);
				if(y > 0 && x > 0) dp[y][x] = Math.max(dp[y][x], dp[y - 1][x - 1] + MAP[y][x]);
			}
		}
	}
	
	int N, M;
	int[][] MAP, dp;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		MAP = new int[N][M];
		dp = new int[N][M];
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < M ; x ++) {
				int value = Integer.parseInt(lineInput.nextToken());
				MAP[y][x] = value;
				dp[y][x] = -1;
			}
		}
		
//		bfs();
		functionIter();
		
//		for(int y = 0 ; y < N ; y ++) {
//			for(int x = 0 ; x < M ; x ++) {
//				System.out.print(String.format("%3d", dp[y][x]));
//			}
//			System.out.println("");
//		}
//		System.out.println("============");
		
		bw.write(String.valueOf(dp[N - 1][M - 1]));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
