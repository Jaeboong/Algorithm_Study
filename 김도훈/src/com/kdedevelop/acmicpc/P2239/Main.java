package com.kdedevelop.acmicpc.P2239;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean checkBlock(int x, int y, int value) {
//		System.out.println("X : " + x + " Y : " + y + " || VALUE : " + (value - 1));
		int startX = (x / 3) * 3;
		int startY = (y / 3) * 3;
		
		boolean[] used = new boolean[9];
		used[value - 1] = true;
		
		for(int i = 0 ; i < 3 ; i ++) {
			for(int j = 0 ; j < 3 ; j ++) {
				int nextX = startX + j;
				int nextY = startY + i;
				int curr = MAP[nextY][nextX] - 1;
				if(curr == -1) continue;
//				System.out.println("X : " + (nextX) + " || Y : " + (nextY + i) + " || CURR : " + curr);
				if(used[curr]) return false;
				used[curr] = true;
			}
		}
		
		return true;
	}
	
	public boolean checkY(int y, int value) {
		boolean[] used = new boolean[9];
		used[value - 1] = true;
		
		for(int i = 0 ; i < 9 ; i ++) {
			int curr = MAP[y][i] - 1;
			if(curr == -1) continue;
			if(used[curr]) return false;
			used[curr] = true;
		}
		
		return true;
	}
	
	public boolean checkX(int x, int value) {
		boolean[] used = new boolean[9];
		used[value - 1] = true;
		
		for(int i = 0 ; i < 9 ; i ++) {
			int curr = MAP[i][x] - 1;
			if(curr == -1) continue;
			if(used[curr]) return false;
			used[curr] = true;
		}
		
		return true;
	}
	
	public void dfs(int depth) {
//		System.out.println(depth);
		if(depth == blankList.size()) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j ++) {
					sb.append(MAP[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			end = true;
		} else {
			int[] blank = blankList.get(depth);
			int x = blank[0];
			int y = blank[1];
			for(int i = 1 ; i < 10 ; i ++) {
				if(end) break;
				if(!checkBlock(x, y, i)) {
					continue;
				}
				if(!checkX(x, i)) {
					continue;
				}
				if(!checkY(y, i)) {
					continue;
				}
				
				MAP[y][x] = i;
				dfs(depth + 1);
				MAP[y][x] = 0;
			}
		}
	}
	
	int N = 9;
	int[][] MAP;
	List<int[]> blankList;
	boolean end;
	public void solution() throws IOException {
		end = false;
		MAP = new int[N][N];
		blankList = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			String line = br.readLine();
			for(int j = 0 ; j < N ; j ++) {
				int value = line.charAt(j) - '0';
				if(value == 0) {
					blankList.add(new int[] {j, i});
				}
				MAP[i][j] = value;
			}
		}
		
//		System.out.println(blankList.size());
		
//		System.out.println(checkBlock(, N, N));
//		for(int i = 0 ; i < 9 ; i ++) {
//			for(int j = 0 ; j < 9 ; j ++) {
//				for(int k = 0 ; k < 9 ; k ++) {
//					System.out.println("X : " + j + " || Y : " + i + " || I : " + (k + 1) + " : " + checkBlock(j, i, k + 1));
//				}
//			}
//		}
		
		dfs(0);
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
