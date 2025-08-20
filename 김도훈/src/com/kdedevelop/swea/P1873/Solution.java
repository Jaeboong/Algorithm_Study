package com.kdedevelop.swea.P1873;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {-1, 1, 0, 0};
	int[] dirY = {0, 0, -1, 1};
	
	public boolean isOutOfBoard(int x, int y) {
		if (x < 0 || x >= W) return true;
		if (y < 0 || y >= H) return true;
		return false;
	}
	
	public void moveFoward() {
		int nextX = x + dirX[dir];
		int nextY = y + dirY[dir];
		if (isOutOfBoard(nextX, nextY)) return;
		if (BOARD[nextY][nextX] == '.') {
			BOARD[nextY][nextX] = BOARD[y][x];
			BOARD[y][x] = '.';
			this.x = nextX;
			this.y = nextY;
		}
	}
	
	public void setDir(char chr, int x, int y) {
		if (BOARD[y][x] == '<') dir = 0;
		if (BOARD[y][x] == '>') dir = 1;
		if (BOARD[y][x] == '^') dir = 2;
		if (BOARD[y][x] == 'v') dir = 3;	
		this.x = x;
		this.y = y;
	}
	
	int H;
	int W;
	char[][] BOARD;
	int N;
	char[] COMMAND;
	int dir;
	int x;
	int y;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputHW = new StringTokenizer(br.readLine());
			H = Integer.parseInt(inputHW.nextToken());
			W = Integer.parseInt(inputHW.nextToken());
			BOARD = new char[H][W];
			for (int y = 0 ; y < H ; y ++) {
				String line = br.readLine();
				for (int x = 0 ; x < W ; x ++) {
					BOARD[y][x] = line.charAt(x);
					if (BOARD[y][x] == '<') setDir(BOARD[y][x], x, y);
					if (BOARD[y][x] == '>') setDir(BOARD[y][x], x, y);
					if (BOARD[y][x] == '^') setDir(BOARD[y][x], x, y);
					if (BOARD[y][x] == 'v') setDir(BOARD[y][x], x, y);
				}
			}
			N = Integer.parseInt(br.readLine());
			COMMAND = new char[N];
			String commandLine = br.readLine();
			for (int i = 0 ; i < N ; i ++) {
				COMMAND[i] = commandLine.charAt(i);
			}
			
			for (char command : COMMAND) {
				switch (command) {
					case 'S' : {
						int bulletX = this.x;
						int bulletY = this.y;
						
						while (true) {
							int nextBulletX = bulletX + dirX[dir];
							int nextBulletY = bulletY + dirY[dir];
							
//							System.out.println("NEXT BULLET X : " + nextBulletX + " || NEXT BULLET Y : " + nextBulletY);
							
							if (isOutOfBoard(nextBulletX, nextBulletY)) break;
							if (BOARD[nextBulletY][nextBulletX] == '#') break;
							if (BOARD[nextBulletY][nextBulletX] == '*') {
								BOARD[nextBulletY][nextBulletX] = '.';
								break;
							}
							bulletX = nextBulletX;
							bulletY = nextBulletY;
						}
						break;
					}
					case 'L' : 
						dir = 0;
						BOARD[y][x] = '<';
						moveFoward();
						break;
					case 'R' : 
						dir = 1;
						BOARD[y][x] = '>';
						moveFoward();
						break;
					case 'U' :
						dir = 2;
						BOARD[y][x] = '^';
						moveFoward();
						break;
					case 'D' :
						dir = 3;
						BOARD[y][x] = 'v';
						moveFoward();
						break;
				}
			}
			
			bw.write("#" + (testCase+1) + " ");
			for (int y = 0 ; y < H ; y ++) {
				for (int x = 0 ; x < W ; x ++) {
					bw.write(BOARD[y][x]);
				}
				bw.write("\n");
			}
//			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
