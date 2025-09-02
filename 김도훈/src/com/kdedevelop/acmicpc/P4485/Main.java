package com.kdedevelop.acmicpc.P4485;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

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
	
	public void dijkstra() {
		Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		boolean[][] visit = new boolean[N][N];
		queue.offer(new int[] {0, 0, MAP[0][0]});
		distance[0][0] = 0;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			
			if(visit[curr[1]][curr[0]]) continue;
			visit[curr[1]][curr[0]] = true;
			
			for(int[] next : VERTEX[curr[1]][curr[0]]) {
				int temp = curr[2] + next[2];
				if(distance[next[1]][next[0]] > temp) {
					distance[next[1]][next[0]] = temp;
					queue.offer(new int[] {next[0], next[1], temp});
				}
			}
		}
	}
	
	int N;
	int[][] MAP;
	List<int[]>[][] VERTEX;
	int[][] distance;
	int result;
	public void solution() throws IOException {
		int testCase = 0;
		while(true) {
			++ testCase;
			N = Integer.parseInt(br.readLine().trim());
			if(N == 0) break;
			
			result = 0;
			VERTEX = new List[N][N];
			distance = new int[N][N];
			MAP = new int[N][N];
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine().trim());
				for(int x = 0 ; x < N ; x ++) {
					VERTEX[y][x] = new ArrayList<>();
					distance[y][x] = Integer.MAX_VALUE;
					int value = inputLine.nextToken().charAt(0) - '0';
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
			
			result = distance[N - 1][N - 1];
			
			
			
			
//			for(int y = 0 ; y < N ; y ++) {
//				for(int x = 0 ; x < N ; x ++) {
//					System.out.print(MAP[y][x] + " ");
//				}
//				System.out.println("");
//			}
			
			bw.write("Problem " + (testCase) + ": " + result + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
