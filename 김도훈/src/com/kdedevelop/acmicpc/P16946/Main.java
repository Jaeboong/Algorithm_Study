package com.kdedevelop.acmicpc.P16946;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) { 
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int convert(int x, int y) {
		return y * M + x;
	}
	
	int[] count;
	int[] parent;
	public int find(int a) {
		if(parent[a] != a) parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA == parentB) return false;
		
		parent[parentA] = parentB;
		count[parentB] += count[parentA];
		return true;
	}
	
	boolean[][] visit;
	public void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {x, y});
		visit[y][x] = true;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int currX = curr[0];
			int currY = curr[1];
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = currX + dirX[dir];
				int nextY = currY + dirY[dir];
				
				if(isOutOfMap(nextX, nextY)) continue;
				if(visit[nextY][nextX]) continue;
				if(MAP[nextY][nextX] > 0) continue;
				
				queue.offer(new int[] {nextX, nextY});
				visit[nextY][nextX] = true;
				
				union(convert(currX, currY), convert(nextX, nextY));
			}
		}
	}
	
	int N, M;
	int[][] MAP;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		MAP = new int[N][M];
		visit = new boolean[N][M];
		count = new int[N * M];
		parent = new int[N * M];
		Arrays.fill(count, 1);
		for(int i = 0 ; i < N ; i ++) {
			String line = br.readLine();
			for(int j = 0 ; j < M ; j ++) {
				int comp = convert(j, i);
				parent[comp] = comp;
				int value = line.charAt(j) - '0';
				MAP[i][j] = value;
			}
		}
		
		StringBuilder result = new StringBuilder();
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(MAP[i][j] > 0) continue;
				if(visit[i][j]) continue;
				
				bfs(j, i);
			}
		}
		
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				if(MAP[i][j] == 1) {
					int sum = 1;
					
					Set<Integer> used = new HashSet<>();
					
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int x = j + dirX[dir];
						int y = i + dirY[dir];
						
						if(isOutOfMap(x, y)) continue;
						if(MAP[y][x] == 1) continue;
						
						int comp = convert(x, y);
						int par = find(comp);
						
						if(used.contains(par)) continue;
						
						sum += count[par];
						used.add(par);
					}
						
					result.append(sum % 10);
				} else {
					result.append("0");
				}
			}
			result.append("\n");
		}
		
		
//		for(int i = 0 ; i < N ; i ++) {
//			for(int j = 0 ; j < M ; j ++) {
//				System.out.print(String.format("%3d", count[convert(j, i)]));
//			}
//			System.out.println("");
//		}
//		System.out.println("+=======+");
//		
//		for(int i = 0 ; i < N ; i ++) {
//			for(int j = 0 ; j < M ; j ++) {
//				System.out.print(String.format("%3d", parent[convert(j, i)]));
//			}
//			System.out.println("");
//		}
//		System.out.println("+=======");
		
		bw.write(result.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
