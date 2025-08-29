package com.kdedevelop.swea.P4014;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.security.auth.spi.LoginModule;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean isOutOfLine(int value) {
		if(value < 0 || value >= N) return true;
		return false;
	}
	
	public int[] getLine(boolean dir, int value) {
		int[] result = new int[N];
		
		for(int i = 0 ; i < N ; i ++) {
			result[i] = dir ? MAP[value][i] : MAP[i][value];
		}
		
		return result;
	}
	
	//dir : 0 -> 가로, 1 -> 세로
	public boolean func(boolean dir, int value) {
		int[] line = getLine(dir, value);
		
//		System.out.println("N : " + N + " || DIR : " + (dir ? "가로" : "세로") + " || VALUE : " + value);
//		System.out.println(Arrays.toString(line));
		
		int height = line[0];
		int count = 0;
		for(int i = 0 ; i < N ; i ++) {
			
//			System.out.print("I : " + i + " || COUNT : " + count + " || HEIGHT : " + height); 
			
			if(height == line[i]) {
//				System.out.println(" IS SAME");
				++ count;
				continue;
			} else if(height - 1 == line[i]) {
//				System.out.print(" IS HIGH NEED CHECK.");
//				System.out.println("");
				int needHeight = height - 1;
				for(int xCount = 0 ; xCount < X ; xCount ++) {
					int next = i + xCount;
//					System.out.print(next + " ");
					if(isOutOfLine(next)) return false;
					if(needHeight != line[next]) return false;
				}
				height = needHeight;
				i += (X - 1);
				count = 0;
//				System.out.println("");
			} else if(height + 1 == line[i]) {
//				System.out.print(" IS LOW. ");
				if(count >= X) {
//					System.out.println("COUNT IS ENOUGH");
					++ height;
					count = 1;
				} else {
//					System.out.println("COUNT FAIL");
					return false;
				}
			} else {
				return false;
			}
		}
		
		return true;
	}
	
	long result;
	int N;
	int X;
	int[][] MAP;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNX = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNX.nextToken());
			X = Integer.parseInt(inputNX.nextToken());
			MAP = new int[N][N];
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x ++) {
					int value = Integer.parseInt(inputLine.nextToken());
					MAP[y][x] = value;
				}
			}
			
			boolean[] dirBoolean = {true, false};
			for(boolean dir : dirBoolean) {
				for(int i = 0 ; i < N ; i ++) {
//					System.out.println("DIR : " + (dir ? "가로" : "세로") + " || VALUE : " + i);

					boolean success = func(dir, i);
//					System.out.println(success ? "GOOD" : "FAIL");
					if(success) ++ result;
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
