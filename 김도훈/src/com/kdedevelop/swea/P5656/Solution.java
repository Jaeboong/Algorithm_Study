package com.kdedevelop.swea.P5656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void printField(boolean[][] field) {
		for(int y = 0 ; y < H ; y ++) {
			for(int x = 0 ; x < W ; x ++) {
				System.out.print((field[y][x] ? "1" : "0") + " ");
			}
			System.out.println("");
		}
//		System.out.println("==============");
	}
	
	public void printField(int[][] field) {
		for(int y = 0 ; y < H ; y ++) {
			for(int x = 0 ; x < W ; x ++) {
				System.out.print(String.format("%2s", field[y][x]));
			}
			System.out.println("");
		}
//		System.out.println("==============");
	}
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfField(int x, int y) {
		if(x < 0 || x >= W) return true;
		if(y < 0 || y >= H) return true;
		return false;
	}
	
	public int removeBrick(int[][] field, boolean[][] remove) {
		int removeCount = 0;
		for(int y = 0 ; y < H ; y ++) {
			for(int x = 0 ; x < W ; x ++) {
				if(remove[y][x]) {
					++ removeCount;
					field[y][x] = 0;
				}
			}
		}
//		if(print) {
//			System.out.println("===========================");
//			printField(field);
//		}
		for(int x = 0 ; x < W ; x ++) {
			int count = H - 1;
			for(int y = H - 1 ; y >= 0 ; y --) {
				if(field[y][x] != 0) {
					int value = field[y][x];
					field[y][x] = 0;
					field[count --][x] = value;
				}
			}
		}
//		if(print) {
//			System.out.println("===========");
//			printField(remove);
//			System.out.println("===========");
//			printField(field);
//			System.out.println("===========================");
//		}
		return removeCount;
	}
	
	public boolean[][] checkBrick(int x, int y, int[][] field) {
		boolean[][] visit = new boolean[H][W];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visit[y][x] = true;
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			
			int currX = curr[0];
			int currY = curr[1];
			int value = field[currY][currX];
			
			for(int dist = 1 ; dist <= value ; dist ++) {
				for(int dir = 0 ; dir < 4 ; dir ++) {
					int nextX = currX + ((dist - 1) * dirX[dir]);
					int nextY = currY + ((dist - 1) * dirY[dir]);
					
					if(isOutOfField(nextX, nextY)) continue;
					if(visit[nextY][nextX]) continue;
					if(field[nextY][nextX] == 0) continue;
					
					queue.offer(new int[] {nextX, nextY});
					visit[nextY][nextX] = true;
				}
			}
		}
		return visit;
	}
	
	public int dropBall(int[][] field, int x) {
		for(int y = 0 ; y < H ; y ++) {
			if(field[y][x] != 0) {
				boolean[][] remove = checkBrick(x, y, field);
				return removeBrick(field, remove);
			}
		}
		return 0;
	}
	
//	boolean print = false;
	public void dfs(int depth, int[] data) {
		if(depth == N) {
//			if(data[0] == 2 && data[1] == 2 && data[2] == 6) print = true;
//			else print = false;
//			System.out.println("===================================");
//			System.out.println(Arrays.toString(data));
			
			int[][] field = new int[H][W];
			for(int y = 0 ; y < H ; y ++) {
				for(int x = 0 ; x < W ; x ++) {
					field[y][x] = BRICKs[y][x];
				}
			}
//			
			int count = 0;
			for(int i = 0 ; i < N ; i ++) {
				count += dropBall(field, data[i]);
//				printField(field);
			}
			if(result < count) {
				result = count;
//				System.out.println(Arrays.toString(data));
			}
//			System.out.println("===================================");
		} else {
			for(int i = 0 ; i < W ; i ++) {
				data[depth] = i;
				dfs(depth + 1, data);
			}
		}
	}
	
	long result;
	int N;
	int W;
	int H;
	int[][] BRICKs;
	int TOTAL_BRICK_COUNT;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNWH = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNWH.nextToken());
			W = Integer.parseInt(inputNWH.nextToken());
			H = Integer.parseInt(inputNWH.nextToken());
			BRICKs = new int[H][W];
			TOTAL_BRICK_COUNT = 0;
			for(int y = 0 ; y < H ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < W ; x ++) {
					int value = Integer.parseInt(lineInput.nextToken());
					if(value != 0) ++ TOTAL_BRICK_COUNT;
					BRICKs[y][x] = value;
				}
			}
			
			dfs(0, new int[N]);
			
			bw.write("#" + (testCase + 1) + " " + (TOTAL_BRICK_COUNT - result) + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
