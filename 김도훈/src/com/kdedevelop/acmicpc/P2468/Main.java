package com.kdedevelop.acmicpc.P2468;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.text.html.HTMLDocument.HTMLReader.HiddenAction;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int flood(int height) {
		int result = 0;
		boolean[][] visit = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		
		for(int y = 0 ; y < N ; y ++) {
			for (int x = 0 ; x < N ; x ++) {
				if(visit[y][x]) continue;
				if(MAP[y][x] < height) continue;
				
				queue.offer(new int[] {x, y});
				visit[y][x] = true;
				++ result;
				
				while(true) {
					if(queue.isEmpty()) break;
					
					int[] curr = queue.poll();
					
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = curr[0] + dirX[dir];
						int nextY = curr[1] + dirY[dir];
						
						if(isOutOfMap(nextX, nextY)) continue;
						if(visit[nextY][nextX]) continue;
						if(MAP[nextY][nextX] < height) continue;
						
						queue.offer(new int[] {nextX, nextY});
						visit[nextY][nextX] = true;
					}
				}
			}
		}
		
		return result;
	}
	
	int N;
	int[][] MAP;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		MAP = new int[N][N];
		int maxHeight = 0;
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int x = 0 ; x < N ; x ++) {
				int value = Integer.parseInt(inputLine.nextToken());
				maxHeight = Math.max(maxHeight, value);
				MAP[y][x] = value;
			}
		}
		
		int result = 0;
		for(int height = maxHeight ; height >= 0 ; height --) {
			int value = flood(height);
			if (result < value) {
				result = value;
			}
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
