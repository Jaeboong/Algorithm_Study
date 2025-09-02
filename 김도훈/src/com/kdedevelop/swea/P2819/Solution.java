package com.kdedevelop.swea.P2819;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfMap(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public void dfs(int depth, int x, int y, char[] data) {
		if(depth == 7) {
			String str = new String(data);
			resultSet.add(str);
		} else {
			data[depth] = MAP[y][x];
			
			for(int dir = 0 ; dir < 4 ; dir ++) {
				int nextX = x + dirX[dir];
				int nextY = y + dirY[dir];
				
				if(isOutOfMap(nextX, nextY)) continue;
				
				dfs(depth + 1, nextX, nextY, data);
			}
		}
	}
	
	Set<String> resultSet;
	long result;
	int N = 4;
	char[][] MAP;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			MAP = new char[N][N];
			resultSet = new HashSet<>();
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine().trim());
				for(int x = 0 ; x < N ; x ++) {
					char value = inputLine.nextToken().charAt(0);
					MAP[y][x] = value;
				}
			}
			
			for(int y = 0 ; y < N ; y ++) {
				for(int x = 0 ; x < N ; x ++) {
					dfs(0, x, y, new char[7]);
				}
			}
			
			result = resultSet.size();
			
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
