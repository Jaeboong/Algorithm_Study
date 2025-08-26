package com.kdedevelop.swea.P5650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//상0 하1 좌2 우3
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	int[] block1Bounce = {1, 3, 0, 2};
	int[] block2Bounce = {3, 0, 1, 2};
	int[] block3Bounce = {2, 0, 3, 1};
	int[] block4Bounce = {1, 2, 3, 0};
	int[] block5Bounce = {1, 0, 3, 2};
	int[][] blockBounce = {block1Bounce, block2Bounce, block3Bounce, block4Bounce, block5Bounce};
	
	
	public int blockBounce(int block, int dir) {
		return blockBounce[block-1][dir];
	}
	
	public boolean isOutOfBoard(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int[] getAnotherHole(int holeNumber, int x, int y) {
		List<int[]> holeList = HOLES.get(holeNumber);
		if(holeList.get(0)[0] == y && holeList.get(0)[1] == x) return holeList.get(1);
		else return holeList.get(0);
	}
	
	public int reverseDir(int dir) {
		switch(dir) {
			case 0 : return 1;
			case 1 : return 0;
			case 2 : return 3;
			case 3 : return 2;
		}
		return 0;
	}
	
	int result;
	int N;
	int[][] BOARD;
	Map<Integer, List<int[]>> HOLES;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine().trim());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			N = Integer.parseInt(br.readLine().trim());
			BOARD = new int[N][N];
			HOLES = new HashMap<>();
			for(int i = 6 ; i < 11 ; i ++) HOLES.put(i, new ArrayList<>());
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine().trim());
				for(int j = 0 ; j < N ; j ++) {
					int object = Integer.parseInt(inputLine.nextToken());
					BOARD[i][j] = object;
					if(object > 5) {
						HOLES.get(object).add(new int[] {i, j});
					}
				}
			}
			
			for(int startY = 0 ; startY < N ; startY ++) {
				for(int startX = 0 ; startX < N ; startX ++) {
					for(int startDir = 0 ; startDir < 4 ; startDir ++) {
						if(BOARD[startY][startX] != 0) continue;
						
						int score = 0;
						int dir = startDir;
						int y = startY + dirY[dir];
						int x = startX + dirX[dir];
						
//						System.out.println("START X : " + startX + " || START Y : " + startY + " || START DIR : " + startDir);
						
						
						while(true) {
							int nextX = x;
							int nextY = y;
							int block = -2;
							
//							System.out.print("START X : " + startX + " || START Y : " + startY + " || START DIR : " + startDir);
//							System.out.println(" || X : " + x + " || Y : " + y + " || DIR : " + dir + " || BLOCK : " + block + " || NEXT X : " + nextX + " || NEXT Y : " + nextY);
							
//							for(int i = 0 ; i < N ; i ++) {
//								for(int j = 0 ; j < N ; j ++) {
//									if(y == i && x == j) System.out.print("+ ");
//									else System.out.print((BOARD[i][j] == -1 ? "X" : BOARD[i][j]) + " ");
//								}
//								System.out.println("");
//							}
//							System.out.println("=========");
							
							if(isOutOfBoard(x, y)) {
								++ score;
								dir = reverseDir(dir);
								nextX = x + dirX[dir];
								nextY = y + dirY[dir];
							} else {
								if(x == startX && y == startY) break;
								
								block = BOARD[y][x];
								
								if(block == -1) {
									break;
								}
								if(block == 0) {
									nextX = x + dirX[dir];
									nextY = y + dirY[dir];
								}
								if(block >= 1 && block <=  5) {
									++ score;
									dir = blockBounce(block, dir);
									nextX = x + dirX[dir];
									nextY = y + dirY[dir];
								}
								if(block >= 6 && block <= 10) {
									int[] hole = getAnotherHole(block, x, y);
									nextX = hole[1] + dirX[dir];
									nextY = hole[0] + dirY[dir];
								}
							}
							
//							System.out.println("X : " + x + " || Y : " + y + " || DIR : " + dir + " || BLOCK : " + block + " || NEXT X : " + nextX + " || NEXT Y : " + nextY);
//							System.out.println("========");
							
							y = nextY;
							x = nextX;
							
//							try {
//								Thread.sleep(500);
//							} catch (InterruptedException e) {}
						}
						
						result = Math.max(result, score);
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
