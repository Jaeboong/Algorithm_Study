package com.kdedevelop.swea.P2115;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void dfs(int depth, boolean[] choose, int x, int y) {
		if(depth == M) {
			int remain = C;
			long result = 0;
			for(int i = 0 ; i < M ; i ++) {
				boolean take = choose[i];
				
				if(take) {
					int honey = remain < HONEY[y][x + i] ? 0 : HONEY[y][x + i];
					result += honey * honey;
					remain -= honey;
				}
			}
			temp = Math.max(result, temp);
//			System.out.println(Arrays.toString(data));
		} else {
			choose[depth] = true;
			dfs(depth + 1, choose, x, y);
			choose[depth] = false;
			dfs(depth + 1, choose, x, y);
		}
	}
	
	public void function() {
		for(int firstY = 0 ; firstY < N ; firstY ++) {
			for(int firstStartX = 0 ; firstStartX < N - (M - 1) ; firstStartX ++) {
				int firstEndX = firstStartX + M;
				
				temp = 0;
				firstMax = 0;
				secondMax = 0;
				dfs(0, new boolean[M], firstStartX, firstY);
				if(firstMax < temp) {
					firstMax = temp;
				}
				
				for(int secondY = 0 ; secondY < N ; secondY ++) {
					for(int secondStartX = 0 ; secondStartX < N - (M - 1) ; secondStartX ++) {
						int secondEndX = secondStartX + M;
						if(firstY == secondY) {
							if(secondEndX >= firstStartX && secondEndX <= firstEndX) continue;
							if(secondStartX >= firstStartX && secondStartX <= firstEndX) continue;
						}
						
						temp = 0;
						dfs(0, new boolean[M], secondStartX, secondY);
						if(secondMax < temp) {
							secondMax = temp;
						}
						
						
						long sum = firstMax + secondMax;
						if(result < sum) {
//							System.out.println("FIRST X : " + firstStartX + ", Y : " + firstY);
//							System.out.println("SECOND X : " + secondStartX + ", Y : " + secondY);
							result = sum;
						}
					}
				}
			}
		}
	}
	
	long firstMax;
	long secondMax;
	long temp;
	long result;
	int N, M, C;
	int[][] HONEY;
	int[][] jar;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNMC = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNMC.nextToken());
			M = Integer.parseInt(inputNMC.nextToken());
			C = Integer.parseInt(inputNMC.nextToken());
			HONEY = new int[N][N];
			jar = new int[N][N];
			temp = 0;
			firstMax = 0;
			secondMax = 0;
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x ++) {
					int value = Integer.parseInt(inputLine.nextToken());
					jar[y][x] = 0;
					HONEY[y][x] = value;
				}
			}
			
//			dfs(0, new boolean[M], new int[M]);
			function();
//			dfs(0, new boolean[M], 2, 3);
//			System.out.println(temp);
			
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
