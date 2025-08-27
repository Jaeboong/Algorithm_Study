package com.kdedevelop.swea.P2382;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public int reverseDirection(int dir) {
		switch (dir) {
			case 0 : return 1;
			case 1 : return 0;
			case 2 : return 3;
			case 3 : return 2;
		}
		throw new RuntimeException();
	}
	
	public boolean isOutOfBoard(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public boolean isChemical(int x, int y) {
		if(x == 0 || x == N - 1) return true;
		if(y == 0 || y == N - 1) return true;
		return false;
	}
	
	public void printMap() {
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < N ; x ++) {
				System.out.print(String.format("%2d", map[y][x]));
			}
			System.out.println("");
		}
		System.out.println("===========");
	}
	
	public int convert(int x, int y) {
		return (y * N) + x;
	}
	
	long result;
	int N;
	int M;
	int K;
	long[][] GROUPs;
	int[][] map;
	boolean[] dead;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNMK = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNMK.nextToken());
			M = Integer.parseInt(inputNMK.nextToken());
			K = Integer.parseInt(inputNMK.nextToken());
			GROUPs = new long[K][];
			map = new int[N][N];
			dead = new boolean[K];
			for(int i = 0 ; i < K ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(inputLine.nextToken());
				int x = Integer.parseInt(inputLine.nextToken());
				int count = Integer.parseInt(inputLine.nextToken());
				int direction = Integer.parseInt(inputLine.nextToken()) - 1;
				long[] group = {x, y, count, direction};
				GROUPs[i] = group;
				map[y][x] = i+1;
			}
			
			for(int time = 0 ; time < M ; time ++) {
			
				Map<Integer, List<Integer>> mergeList = new HashMap<>();
				
				
				boolean[][] mergeMap = new boolean[N][N];
				for(int i = 0 ; i < K ; i ++) {
					if(dead[i]) continue;
					
					long[] group = GROUPs[i];
					
					int x = (int) group[0];
					int y = (int) group[1];
					long count = group[2];
					int direction = (int) group[3];
					
					int nextX = x + dirX[direction];
					int nextY = y + dirY[direction];
					int comp = convert(nextX, nextY);
					
					group[0] = nextX;
					group[1] = nextY;
					
					if(isChemical(nextX, nextY)) {
						count /= 2;
						group[2] = count;
						if(count == 0) {
							dead[i] = true;
							continue;
						}
						direction = reverseDirection(direction);
						group[3] = direction;
					}
					
					if(!mergeMap[nextY][nextX]) {
						mergeMap[nextY][nextX] = true;
						mergeList.put(comp, new LinkedList<>());
					}
					mergeList.get(comp).add(i);
				}
				
				for(int merge : mergeList.keySet()) {
					List<Integer> mergeGroups = mergeList.get(merge);
					
					long totalCount = 0;
					long maxCount = 0;
					int mainIndex = 0;
					
					for(int groupIndex : mergeGroups) {
						long[] group = GROUPs[groupIndex];
						long count = group[2];
					
						if(maxCount < count) {
							maxCount = count;
							mainIndex = groupIndex;
						}
						
						totalCount += count;
					}
					
					for(int groupIndex : mergeGroups) {
						if(mainIndex == groupIndex) continue;
						dead[groupIndex] = true;
					}
					
					GROUPs[mainIndex][2] = totalCount;
				}
				
//				for(int i = 0 ; i < K ; i ++) {
//					System.out.println(((char) (i + 'A')) + " || " + Arrays.toString(GROUPs[i]) + " || " + (dead[i] ? "X" : "O"));
//				}
//				System.out.println("==================");
			}
			
			for(int i = 0 ; i < K ; i ++) {
				if(dead[i]) continue;
				result += GROUPs[i][2];
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
