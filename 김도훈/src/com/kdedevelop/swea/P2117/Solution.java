package com.kdedevelop.swea.P2117;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
	
	public int getSpendMoney(int K) {
		return K * K + (K - 1) * (K - 1);
	}
	
	public boolean isIn(int[] center, int distance, int[] house) {
		int distY = center[1] - house[1];
		if(distY < 0) distY *= -1;
		
		int distX = center[0] - house[0];
		if(distX < 0) distX *= -1;
		
		return distX + distY < distance;
	}
	
	public int function(int[] center) {
		int result = 0;
		for(int i = 1 ; i < 40 ; i ++) {
			int spendMoney = getSpendMoney(i);
			int count = 0;
			for(int[] house : HOUSE) {
				boolean isIn = isIn(center, i, house);
				if(isIn) ++ count;
			}
			int getMoney = count * M;
			if(spendMoney > getMoney) continue;
			result = Math.max(result, count);
		}
		return result;
	}
	
	long result;
	int N, M;
	boolean[][] MAP;
	List<int[]> HOUSE;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			MAP = new boolean[N][N];
			HOUSE = new LinkedList<>();
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int j = 0 ; j < N ; j ++) {
					boolean value = lineInput.nextToken().charAt(0) == '1';
					if(value) HOUSE.add(new int[] {j, i});
					MAP[i][j] = value;
				}
			}
			
			for(int i = 0 ; i < N ; i ++) {
				for(int j = 0 ; j < N ; j ++) {
					int house = function(new int[] {j, i});
					result = Math.max(result, house);
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
