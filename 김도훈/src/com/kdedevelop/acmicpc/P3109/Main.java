package com.kdedevelop.acmicpc.P3109;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	public boolean isOutOfMatrix(int x, int y) {
		if (x < 0 || x >= C) return true;
		if (y < 0 || y >= R) return true;
		return false;
	}
	
	public boolean isBlocked(int x, int y) {
		return matrix[y][x];
	}
	
	int[] disX = {1, 1, 1};
	int[] disY = {-1, 0, 1};
	
	public void dfs(int x, int y) {
		if (x == C-1) {
			end = true;
			result++;
		}
		
		for (int dist = 0 ; dist < 3 ; dist ++) {
			int nextX = x + disX[dist];
			int nextY = y + disY[dist];
			
			if (isOutOfMatrix(nextX, nextY)) continue;
			if (isBlocked(nextX, nextY)) continue;
			
//			System.out.println("NEXT X : " + nextX + " || NEXT Y : " + nextY);
			
			matrix[nextY][nextX] = true;
			dfs(nextX, nextY);
			if (end) return;
		}
	}

	int result = 0;
	boolean end;
	int R;
	int C;
	boolean[][] matrix;
	
	public void solution() throws IOException {
		StringTokenizer inputRC = new StringTokenizer(br.readLine());
		R = Integer.parseInt(inputRC.nextToken());
		C = Integer.parseInt(inputRC.nextToken());
		matrix = new boolean[R][C];
		for (int lineIndex = 0 ; lineIndex < R ; lineIndex ++) {
			String line = br.readLine();
			for (int charIndex = 0 ; charIndex < C ; charIndex ++) {
				boolean building = line.charAt(charIndex) == 'x' ? true : false;
				matrix[lineIndex][charIndex] = building;
			}
		}
		
//		for (int i = 0 ; i < R ; i ++) {
//			for (int j = 0 ; j < C ; j ++) {
//				System.out.print(matrix[i][j] + " ");
//			}
//			System.out.println("");
//		}
		
		for (int i = 0 ; i < R ; i ++) {
			end = false;
			dfs(0, i);
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
}
