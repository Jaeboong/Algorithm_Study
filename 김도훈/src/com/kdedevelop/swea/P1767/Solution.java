package com.kdedevelop.swea.P1767;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isConnectedWithWall(int[] processor) {
		int x = processor[0];
		int y = processor[1];
		if(x == 0 || x == N - 1) return true;
		if(y == 0 || y == N - 1) return true;
		return false;
	}
	
	public boolean isOutOfBoard(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int isAvailable(int x, int y, int dir, boolean[][][] cableBoards) {
		int count = 0;
		int currentX = x;
		int currentY = y;
		while(true) {
			int nextX = currentX + dirX[dir];
			int nextY = currentY + dirY[dir];
			
			if(isOutOfBoard(nextX, nextY)) return count;
			if(BOARD[nextY][nextX]) return 0;
			for(boolean[][] cableBoard : cableBoards) {
				if(cableBoard[nextY][nextX]) return 0;
			}
			
			++ count;
			
			currentX = nextX;
			currentY = nextY;
		}
	}
	
	public boolean[][] fill(int x, int y, int dir) {
		boolean[][] board = new boolean[N][N];
		int currentX = x + dirX[dir];
		int currentY = y + dirY[dir];
		while(true) {
			if(isOutOfBoard(currentX, currentY)) break;
			board[currentY][currentX] = true;
			currentX = currentX + dirX[dir];
			currentY = currentY + dirY[dir];
		}
		return board;
	}
	
	int maxProcessor;
	int minCableLength;
	public void dfs(int depth, int totalProcessorCount, int totalCableLength, boolean[][][] cableBoards) {
		if(depth == PROCESSOR_COUNT) {
			if(maxProcessor < totalProcessorCount) {
				maxProcessor = totalProcessorCount;
				minCableLength = Integer.MAX_VALUE;
			}
			if(maxProcessor == totalProcessorCount) {
//				System.out.println("DEPTH : " + depth + " || TOTAL PROCESSOR COUNT : " + totalProcessorCount + " || TOTAL CABLE LENGTH : " + totalCableLength);
//				
//				for(int i = 0 ; i < N ; i ++) {
//					for(int j = 0 ; j < N ; j ++) {
//						System.out.print((cableBoard[i][j] ? "1" : "0") + " ");
//					}
//					System.out.println("");
//				}
//				System.out.println("");
				
				minCableLength = Math.min(minCableLength, totalCableLength);
			}
		} else {
			int[] processor = PROCESSORs.get(depth);
			int x = processor[0];
			int y = processor[1];
			
			if(isConnectedWithWall(processor)) {
				boolean[][] cableBoard = new boolean[N][N];
				cableBoards[depth] = cableBoard;
				dfs(depth + 1, totalProcessorCount + 1, totalCableLength, cableBoards);
			} else {
				for(int dir = 0 ; dir < 4 ; dir ++) {
					int cableLength = isAvailable(x, y, dir, cableBoards);
					if(cableLength == 0) continue;
					
					boolean[][] cableBoard = fill(x, y, dir);
					cableBoards[depth] = cableBoard;
					dfs(depth + 1, totalProcessorCount + 1, cableLength + totalCableLength, cableBoards);
					
				}
				boolean[][] cableBoard = new boolean[N][N];
				cableBoards[depth] = cableBoard;
				dfs(depth + 1, totalProcessorCount, totalCableLength, cableBoards);
			}
		}
	}
	
	
	int result;
	int N;
	boolean[][] BOARD;
	List<int[]> PROCESSORs;
	int PROCESSOR_COUNT;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			BOARD = new boolean[N][N];
			PROCESSORs = new ArrayList<>(12);
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x ++) {
					boolean processor = lineInput.nextToken().charAt(0) == '1';
					if(processor) PROCESSORs.add(new int[] {x, y});
					BOARD[y][x] = processor;
				}
			}
			PROCESSOR_COUNT = PROCESSORs.size();
			maxProcessor = Integer.MIN_VALUE;
			minCableLength = Integer.MAX_VALUE;
			
			dfs(0, 0, 0, new boolean[PROCESSOR_COUNT][N][N]);
			
			result = minCableLength;
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
