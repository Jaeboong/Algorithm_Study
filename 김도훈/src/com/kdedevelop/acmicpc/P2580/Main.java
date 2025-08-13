package com.kdedevelop.acmicpc.P2580;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}

public class Main {
	
	static boolean finish = false;
	public static void dfs(int n, int r, int[][] sudoku, List<Point> blankData, int depth) {
		if (depth == r) {
			finish = true;
			return;
		} else {
			Point blank = blankData.get(depth);
			Set<Integer> possible = new HashSet<>();
			for (int i = 1 ; i < 10 ; i ++) possible.add(i);
			
			
			int x = blank.x;
			for (int i = 0 ; i < n ; i ++) {
				int value = sudoku[i][x];
//				System.out.println("I : " + i + " || X : " + x + "|| VALUE : " + value);
				possible.remove(value);
			}
			
			int y = blank.y;
			for (int i = 0 ; i < n ; i ++) {
				int value = sudoku[y][i];
//				System.out.println("I : " + i + " || Y : " + y + " || VALUE : " + value);
				possible.remove(value);
			}
			
//			System.out.println("X : " + x + " || Y : " + y);
			
			int positionX = (x/3)*3;
			int positionY = (y/3)*3;
			
//			System.out.println("POSITION X : " + positionX + " || POSITION Y : " + positionY);
			for (int i = 0 ; i < 3 ; i ++) {
				for (int j = 0 ; j < 3 ; j ++) {
					int value = sudoku[positionY+i][positionX+j];
//					System.out.println("Y : " + ((positionY*3)+i) + " || X : " + ((positionX*3)+j) + " || VALUE : " + value);
					possible.remove(value);
				}
			}
//			System.out.println("=====");
			
			for (int value : possible) {
				if (finish) return;
				sudoku[y][x] = value;
//				System.out.println("Y : " + y + " || X : " + x + " || VALUE : " + value);
				dfs(n, r, sudoku, blankData, depth+1);
				if (!finish) sudoku[y][x] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = 9;
		int[][] sudoku = new int[N][N];
		
		List<Point> blankPointList = new ArrayList<>();
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				int input = Integer.parseInt(inputLine.nextToken());
				if (input == 0) blankPointList.add(new Point(j, i));
				sudoku[i][j] = input;
			}
		}
//		System.out.println(blankPointList.size());
		
		dfs(N, blankPointList.size(), sudoku, blankPointList, 0);
		
		for (int y = 0 ; y < N ; y ++) {
			for (int x = 0 ; x < N ; x ++) {
				bw.write(String.valueOf(sudoku[y][x]) + " ");
			}
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
