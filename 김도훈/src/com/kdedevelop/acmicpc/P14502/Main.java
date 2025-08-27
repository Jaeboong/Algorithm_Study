package com.kdedevelop.acmicpc.P14502;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int converter(int x, int y) {
		return (y * M) + x;
	}
	
	public int[] converter(int comp) {
		int y = comp / M;
		int x = comp % M;
		return new int[] {x, y};
	}
	
	public void draw(int[] data, int value) {
		for(int comp : data) {
			int[] xy = converter(comp);
			MAP[xy[1]][xy[0]] = value;
		}
	}
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public void printVisit() {
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				System.out.print((visit[y][x] ? "1" : "0") + " ");
			}
			System.out.println("");
		}
		System.out.println("=========");
	}
	
	boolean[][] visit;
	public int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visit[y][x] = true;
		int count = 0;
		
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
				
				int nextBlock = MAP[nextY][nextX];
				if(nextBlock == 1) continue;
				if(nextBlock == 0) ++ count;
				
				queue.offer(new int[] {nextX, nextY});
				visit[nextY][nextX] = true;
			}
		}
		return count;
	}
	
	public void printMap() {
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				System.out.print(MAP[y][x] + " ");
			}
			System.out.println("");
		}
		System.out.println("=========");
	}
	
	public void dfs(int depth, int start, int[] data) {
		if(depth == 3) {
			draw(data, 1);
			
			int count = 0;
			visit = new boolean[N][M];
			for(int[] virus : VIRUS) {
				count += bfs(virus[0], virus[1]);
			}
			if(result > count) {
				result = count;
			}
			
			draw(data, 0);
		} else {
			for(int i = start + 1 ; i < N * M ; i ++) {
				int[] xy = converter(i);
				if(MAP[xy[1]][xy[0]] != 0) continue;
				data[depth] = i;
				dfs(depth + 1, i, data);
			}
		}
	}
	
	int N;
	int M;
	int[][] MAP;
	List<int[]> VIRUS;
	int EMPTY_SAPCE_COUNTER;
	int result;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		MAP = new int[N][M];
		VIRUS = new LinkedList<>();
		EMPTY_SAPCE_COUNTER = 0;
		result = Integer.MAX_VALUE;
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < M ; x ++) {
				int block = Integer.parseInt(lineInput.nextToken());
				if(block == 0) {
					++ EMPTY_SAPCE_COUNTER;
				}
				if(block == 2) {
					int[] virus = {x, y};
					VIRUS.add(virus);
				}
				MAP[y][x] = block;
			}
		}
		
		//added pillar count
		EMPTY_SAPCE_COUNTER -= 3;
		dfs(0, -1, new int[3]);
		
		bw.write(String.valueOf(EMPTY_SAPCE_COUNTER - result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
