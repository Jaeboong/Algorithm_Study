package com.kdedevelop.acmicpc.P17144;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	//상 0 || 하 1 || 좌 2 || 우 3
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= C) return true;
		if(y < 0 || y >= R) return true;
		return false;
	}
	
	public boolean isOutOfMap(int x, int y, int minX, int maxX, int minY, int maxY) {
		if(x <= minX || x >= maxX) return true;
		if(y <= minY || y >= maxY) return true;
		return false;
	}
	
	public void clean() {
		for(int updown = 0 ; updown < 2 ; updown ++) {
//			System.out.println("UP DOWN : " + updown);
			int x = 0;
			int y = FILTER[updown];
			MAP[y][x] = 0;
			for(int dir : cleanDir[updown]) {
//				System.out.println("DIR : " + dir);
				while(true) {
//					System.out.println("===============================");
//					System.out.println("X : " + x + " || Y : " + y + " || AMOUNT : " + MAP[y][x]);
					int nextX = x + dirX[dir];
					int nextY = y + dirY[dir];
					
					if(isOutOfMap(nextX, nextY, cleanMinMax[updown][0], cleanMinMax[updown][2], cleanMinMax[updown][1], cleanMinMax[updown][3])) {
//						System.out.println("IS OUT OF MAP");
						break;
					}
					
					int nextAmount = MAP[nextY][nextX];
//					System.out.println("NEXT X : " + nextX + " || NEXT Y : " + nextY + " || NEXT AMOUNT : " + nextAmount);
					
					MAP[y][x] = nextAmount;
					
					x = nextX;
					y = nextY;
					
					MAP[FILTER[updown]][0] = 0;
				}
			}
		}
	}
	
	public void diffusion() {
		int[][] nextMap = new int[R][C];
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				if(MAP[y][x] != 0) {
					int remain = MAP[y][x];
					int amount = remain / 5;
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = x + dirX[dir];
						int nextY = y + dirY[dir];
						
						if(isOutOfMap(nextX, nextY)) continue;
						if(nextX == 0 && nextY == FILTER[0]) continue;
						if(nextX == 0 && nextY == FILTER[1]) continue;
						
						nextMap[nextY][nextX] += amount;
						remain -= amount;
					}
					nextMap[y][x] += remain;
				}
			}
		}
		MAP = nextMap;
	}
	
	public void print() {
		for(int i = 0 ; i < R ; i ++) {
			for(int j = 0 ; j < C ; j ++) {
				System.out.print(String.format("%3d", MAP[i][j]));
			}
			System.out.println("");
		}
		System.out.println("=============");
	}
	
	public void function() {
		for(int time = 0 ; time < T ; time ++) {
			diffusion();
//			print();
			
			clean();
//			print();
//			System.out.println("=================================");
		}
	}
	
	int R, C, T;
	int[][] MAP;
	int[] FILTER;
	
//	int[][] cleanDir = {{3, 0, 2, 1}, {3, 1, 2, 0}};
	//상우하좌, 하우상좌
	int[][] cleanDir = {{0, 3, 1, 2}, {1, 3, 0, 2}};
	int[][] cleanMinMax;
	
	public void solution() throws IOException {
		StringTokenizer inputRCT = new StringTokenizer(br.readLine());
		R = Integer.parseInt(inputRCT.nextToken());
		C = Integer.parseInt(inputRCT.nextToken());
		T = Integer.parseInt(inputRCT.nextToken());
		FILTER = new int[] {-1, -1};
		MAP = new int[R][C];
		for(int y = 0 ; y < R ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < C ; x ++) {
				int value = Integer.parseInt(lineInput.nextToken());
				if(value == -1) {
					if(FILTER[0] == -1) FILTER[0] = y;
					else                FILTER[1] = y;
					value = 0;
				}
				MAP[y][x] = value;
			}
		}
		
		cleanMinMax = new int[][] {{-1, -1, C, FILTER[1]}, {-1, FILTER[0], C, R}};
		
//		System.out.println(Arrays.toString(cleanMinMax[0]));
//		System.out.println(Arrays.toString(cleanMinMax[1]));
//		System.out.println(Arrays.toString(FILTER));
		
		function();
		
		int result = 0;
		for(int y = 0 ; y < R ; y ++) {
			for(int x = 0 ; x < C ; x ++) {
				int value = MAP[y][x];
				if(value == -1 || value == 0) continue;
				result += value;
			}
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
