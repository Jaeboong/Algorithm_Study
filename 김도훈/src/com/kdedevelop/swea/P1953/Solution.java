package com.kdedevelop.swea.P1953;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//상 하 좌 우
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	int[][] pipeDirData = {{}, {0, 1, 2, 3}, {0, 1}, {2, 3}, {0, 3}, {1, 3}, {1, 2}, {0, 2}};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public boolean isConnected(int dir, int nextObject) {
		switch(dir) {
			case 0 : 
				if(nextObject == 1) return true;
				if(nextObject == 2) return true;
				if(nextObject == 5) return true;
				if(nextObject == 6) return true;
				break;
			case 1 : ;
				if(nextObject == 1) return true;
				if(nextObject == 2) return true;
				if(nextObject == 4) return true;
				if(nextObject == 7) return true;
				break;
			case 2 : ;
				if(nextObject == 1) return true;
				if(nextObject == 3) return true;
				if(nextObject == 4) return true;
				if(nextObject == 5) return true;
				break;
			case 3 : ;
				if(nextObject == 1) return true;
				if(nextObject == 3) return true;
				if(nextObject == 6) return true;
				if(nextObject == 7) return true;
				break;
		}
		return false;
	}
	
	public void dijkstra() {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		queue.offer(new int[] {C, R, 0});
		distance[R][C] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
//			System.out.println("======================");
//			printDistance();
			
			int[] curr = queue.poll();
//			System.out.println("CURR : " + Arrays.toString(curr));
			
			if(visit[curr[1]][curr[0]]) {
//				System.out.println("is already visit");
				continue;
			}
			visit[curr[1]][curr[0]] = true;
			
			int pipe = UNDER_GROUND[curr[1]][curr[0]];
//			System.out.println("pipe : " + pipe);
			
			for(int dir : pipeDirData[pipe]) {
//				System.out.println("dir : " + dir);
				int nextX = curr[0] + dirX[dir];
				int nextY = curr[1] + dirY[dir];
//				System.out.println("next x : " + nextX + ", y : " + nextY);
				
				if(isOutOfMap(nextX, nextY)) {
//					System.out.println("IS OUT OF MAP");
					continue;
				}
				if(visit[nextY][nextX]) {
//					System.out.println("IS ALREADY VISIT");
					continue;
				}
				
				int nextObject = UNDER_GROUND[nextY][nextX];
				if(!isConnected(dir, nextObject)) {
//					System.out.println("is not connected");
					continue;
				}
				
				int nextDistance = curr[2] + 1;
				
				if(distance[nextY][nextX] > nextDistance) {
					queue.offer(new int[] {nextX, nextY, nextDistance});
					distance[nextY][nextX] = nextDistance;
				}
			}
		}
	}
	
//	public void dijkstra() { 
//		Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[1], e2[1])); 
//		distance[START] = 0; 
//		queue.offer(new int[] {START, 0}); 
//		int count = 0; 
//		while(true) { 
//			// System.out.println("COUNT : " + count + " || SIZE : " + queue.size()); 
//			if(queue.isEmpty()) break; 
//			if(count == V) break; 
//			
//			int[] curr = queue.poll(); 
//			
//			if(visit[curr[0]]) continue; 
//			visit[curr[0]] = true; 
//			++ count; 
//			
//			for(int[] next : VERTEX[curr[0]]) { 
//				if(visit[next[0]]) continue; 
//				
//				int temp = curr[1] + next[1]; 
//				if(distance[next[0]] > temp) { 
//					distance[next[0]] = temp; 
//					queue.offer(new int[] {next[0], temp}); 
//				} 
//			} 
//		} 
//	}
	
	public void printDistance() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				System.out.print(String.format("%3d", distance[i][j] == Integer.MAX_VALUE ? -1 : distance[i][j]));
			}
			System.out.println("");
		}
		System.out.println("==============");
	}
	
	public void printUnderGround() {
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				System.out.print(String.format("%2d", UNDER_GROUND[i][j]));
			}
			System.out.println("");
		}
		System.out.println("==============");
	}
	
	long result;
	int N, M, R, C, L;
	int[][] UNDER_GROUND;
	int[][] distance;
	boolean[][] visit;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNMRCL = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNMRCL.nextToken());
			M = Integer.parseInt(inputNMRCL.nextToken());
			R = Integer.parseInt(inputNMRCL.nextToken());
			C = Integer.parseInt(inputNMRCL.nextToken());
			L = Integer.parseInt(inputNMRCL.nextToken());
			
			UNDER_GROUND = new int[N][M];
			distance = new int[N][M];
			visit = new boolean[N][M];
			
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < M ; x ++) {
					distance[y][x] = Integer.MAX_VALUE;
					int value = Integer.parseInt(lineInput.nextToken());
					UNDER_GROUND[y][x] = value;
				}
			}
			
//			printUnderGround();
			
			dijkstra();
			
//			printDistance();
			
			for(int y = 0 ; y < N ; y ++) {
				for(int x = 0 ; x < M ; x ++) {
					if(distance[y][x] < L) {
						++ result;
					}
				}
			}
			
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
