package com.kdedevelop.acmicpc.P17135;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
//	public boolean test(int[] data) {
//		if(data[0] != 0) return false;
//		if(data[1] != 2) return false;
//		if(data[2] != 4) return false;
//		return true;
//	}
//	
//	public void printBoard(boolean[][] board) {
//		for(int y = 0 ; y < N ; y ++) {
//			for(int x = 0 ; x < M ; x ++) {
//				System.out.print((board[y][x] ? "1" : "0") + " ");
//			}
//			System.out.println("");
//		}
//		System.out.println("=============");
//	}
	
	public boolean[][] copyBoard() {
		boolean[][] board = new boolean[N][M];
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < M ; x ++) {
				board[y][x] = MAP[y][x];
			}
		}
		return board;
	}
	
	//상 하 좌 우
//	int[] dirX = {0, 0, -1, 1};
//	int[] dirY = {-1, 1, 0, 0};
	
	int[] dirX = {-1, 0, 1};
	int[] dirY = {0, -1, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= M) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int[] findEnemy(int archerX, boolean[][] board) {
		boolean[][] visit = new boolean[N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {archerX, N, 0});
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int x = curr[0];
			int y = curr[1];
			int dist = curr[2];
			
			if(!isOutOfMap(x, y)) if(board[y][x]) return curr;
			
			for(int dir = 0 ; dir < 3 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				int nextDist = dist + 1;
				
				if(nextDist > D) continue;
				if(isOutOfMap(nextX, nextY)) continue;
				if(visit[nextY][nextX]) continue;
				
				queue.offer(new int[] {nextX, nextY, nextDist});
				visit[nextY][nextX] = true;
			}
		}
		
		return null;
	}
	
	public int killEnemy(List<int[]> enemyList, boolean[][] board) {
		int hunt = 0;
		for(int[] enemy : enemyList) {
			int x = enemy[0];
			int y = enemy[1];
			if(board[y][x]) {
				board[y][x] = false;
				++ hunt;
			}
		}
		return hunt;
	}
	
	public void moveBoard(boolean[][] board) {
		for(int x = 0 ; x < M ; x ++) {
			for(int y = N - 1 ; y > 0 ; y --) {
				board[y][x] = board[y - 1][x];
			}
		}
		for(int x = 0 ; x < M ; x ++) {
			board[0][x] = false;
		}
	}
	
	public int play(int[] archerXPosition) {
		int hunt = 0;
		
		boolean[][] board = copyBoard();
		
		for(int time = 0 ; time < N ; time ++) {
			
//			if(test(archerXPosition)) {
//				System.out.println("===================");
//				System.out.println("TIME : " + time);
//				printBoard(board);
//			}
			
			List<int[]> enemyList = new LinkedList<>();
			for(int archerX : archerXPosition) {
				int[] enemy = findEnemy(archerX, board);
				if(enemy == null) continue;
				enemyList.add(enemy);
//				if(test(archerXPosition)) System.out.println(Arrays.toString(enemy));
			}
			
			hunt += killEnemy(enemyList, board);
			
//			if(test(archerXPosition)) {
//				System.out.println("HUNT : " + hunt);
//				System.out.println("===================");
//			}
			
			moveBoard(board);
		}
		
		return hunt;
	}
	
	int result;
	public void dfs(int depth, int start, int[] data) {
		if(depth == 3) {
			int hunt = play(data);
			if(result < hunt) {
//				System.out.println(Arrays.toString(data) + " || HUNT : " + hunt);
				result = hunt;
			}
		} else {
			for(int i = start + 1 ; i < M ; i ++) {
				data[depth] = i;
				dfs(depth + 1, i, data);
			}
		}
	}
	
	int N;
	int M;
	int D;
	boolean[][] MAP;
	public void solution() throws IOException {
		StringTokenizer inputNMD = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNMD.nextToken());
		M = Integer.parseInt(inputNMD.nextToken());
		D = Integer.parseInt(inputNMD.nextToken());
		MAP = new boolean[N][M];
		result = 0;
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < M ; x ++) {
				boolean enemy = inputLine.nextToken().charAt(0) == '1';
				MAP[y][x] = enemy;
			}
		}
		
		dfs(0, -1, new int[3]);
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
