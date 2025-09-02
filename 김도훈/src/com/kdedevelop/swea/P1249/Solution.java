package com.kdedevelop.swea.P1249;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void dijkstra() {
		Queue<int[]> queue = new PriorityQueue<>((e1, e2) -> Integer.compare(e1[2], e2[2]));
		distance[START[1]][START[0]] = 0;
		queue.offer(new int[] {START[0], START[1], 0});
		
		int count = 0;
		while(true) {
			if(queue.isEmpty()) break;
			if(count == N * N) break;
			
			int[] curr = queue.poll();
			
			if(visit[curr[1]][curr[0]]) continue;
			visit[curr[1]][curr[0]] = true;
			++ count;
			
			for(int[] next : VERTEX[curr[1]][curr[0]]) {
				int temp = curr[2] + next[2];
				if(distance[next[1]][next[0]] > temp) {
					distance[next[1]][next[0]] = temp;
					queue.offer(new int[] {next[0], next[1], temp});
				}
			}
		}
	}
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	long result;
	int N;
	int[][] MAP;
	int[] START = {0, 0};
	int[] END;
	List<int[]>[][] VERTEX;
	boolean[][] visit;
	int[][] distance;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			N = Integer.parseInt(br.readLine().trim());
			END = new int[] {N - 1, N - 1};
			MAP = new int[N][N];
			VERTEX = new List[N][N];
			visit = new boolean[N][N];
			distance = new int[N][N];
			for(int y = 0 ; y < N ; y ++) {
				String line = br.readLine().trim();
				for(int x = 0 ; x < N ; x ++) {
					VERTEX[y][x] = new ArrayList<>();
					distance[y][x] = Integer.MAX_VALUE;
					int value = line.charAt(x) - '0';
					MAP[y][x] = value;
				}
			}
			
			for(int y = 0 ; y < N ; y ++) {
				for(int x = 0 ; x < N ; x ++) {
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = x + dirX[dir];
						int nextY = y + dirY[dir];
						
						if(isOutOfMap(nextX, nextY)) continue;
						
						int weight = MAP[nextY][nextX];
						
						VERTEX[y][x].add(new int[] {nextX, nextY, weight});
					}
				}
			}
			
			dijkstra();
			
			result = distance[END[1]][END[0]];
			
//			for(int y = 0 ; y < N ; y ++) {
//				for(int x = 0 ; x < N ; x ++) {
//					System.out.print(distance[y][x] + " ");
//				}
//				System.out.println("");
//			}
//				System.out.println("========");
//			for(int y = 0 ; y < N ; y ++) {
//				for(int x = 0 ; x < N ; x ++) {
//					System.out.print(MAP[y][x] + " ");
//				}
//				System.out.println("");
//			}
//				System.out.println("========");
			
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
