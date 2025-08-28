package com.kdedevelop.jungol.P2543;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int[][][] tiles = {
			{{0, 1}, {1, 1}},
			{{2, 0}, {2, 2}},
			{{3, 3}, {0, 3}},
			{{4, 4}, {4, 0}}
	};
	
	public int getQuadrant(int startX, int startY, int endX, int endY, int holeX, int holeY) {
		int middleX = (startX + endX) / 2;
		int middleY = (startY + endY) / 2;
		
//		System.out.println("START X : " + startX + ", Y : " + startY + " || MIDDLE X : " + middleX + ", Y : " + middleY + " || END X : " + endX + ", Y : " + endY + " || HOLE X : " + holeX + " || Y " + holeY);
		
		if(holeX <= middleX && holeY <= middleY) return 0;
		if(holeX > middleX && holeY <=middleY) return 1;
		if(holeX <= middleX && holeY > middleY) return 2;
		if(holeX > middleX && holeY > middleY) return 3;
		
		throw new RuntimeException("???");
	}
	
	public void function(int startX, int startY, int endX, int endY, int holeX, int holeY) {
		int size = endX - startX;
		
		if(size >= 1) {
			
			int middleX = (startX + endX) / 2;
			int middleY = (startY + endY) / 2;
			
			int quadrant = getQuadrant(startX, startY, endX, endY, holeX, holeY);
			
			int[][] tile = tiles[quadrant];
			
//			for(int[] tileLine : tile) {
//				for(int tileShard : tileLine) {
//					System.out.print(tileShard + " ");
//				}
//				System.out.println("");
//			}
			
			for(int y = 0 ; y < 2 ; y ++) {
				int targetY = middleY + y;
				for(int x = 0 ; x < 2 ; x ++) {
//					System.out.print(tile[y][x] + " ");
					int targetX = middleX + x;
					if(tile[y][x] == 0) continue;
					FLOOR[targetY][targetX] = tile[y][x];
				}
//				System.out.println("");
			}
//			System.out.println("");
			
//			System.out.println(quadrant);
			
			function(startX,      startY,      middleX, middleY, quadrant == 0 ? holeX : middleX,     quadrant == 0 ? holeY : middleY);
			function(middleX + 1, startY,      endX,    middleY, quadrant == 1 ? holeX : middleX + 1, quadrant == 1 ? holeY : middleY);
			function(startX,      middleY + 1, middleX, endY,    quadrant == 2 ? holeX : middleX,     quadrant == 2 ? holeY : middleY + 1);
			function(middleX + 1, middleY + 1, endX,    endY,    quadrant == 3 ? holeX : middleX + 1, quadrant == 3 ? holeY : middleY + 1);
		}
	}
	
	int N;
	int[][] FLOOR;
	int HOLE_X;
	int HOLE_Y;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		FLOOR = new int[N][N];
		StringTokenizer inputHole = new StringTokenizer(br.readLine());
		HOLE_Y = Integer.parseInt(inputHole.nextToken());
		HOLE_X = Integer.parseInt(inputHole.nextToken());
		
//		for(int[][] tile : tiles) {
//			for(int[] tileLine : tile) {
//				for(int tileShard : tileLine) {
//					System.out.print(tileShard + " ");
//				}
//				System.out.println("");
//			}
//			System.out.println("");
//			System.out.println("");
//		}
		
		function(0, 0, N - 1, N - 1, HOLE_X, HOLE_Y);
		
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < N ; x ++) {
				bw.write(String.valueOf(FLOOR[y][x]) + " ");
			}
			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
