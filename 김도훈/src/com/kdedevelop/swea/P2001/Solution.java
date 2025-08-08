package com.kdedevelop.swea.P2001;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\input (4).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(inputNM.nextToken());
			int M = Integer.parseInt(inputNM.nextToken());
			
			int[][] matrix = new int[N+1][N+1];
			int[][] sumMatrix = new int[N+1][N+1];
			for (int y = 1 ; y <= N ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for (int x = 1 ; x <= N ; x ++) {
					int input = Integer.parseInt(lineInput.nextToken());
					matrix[y][x] = input;
					sumMatrix[y][x] = (sumMatrix[y-1][x] + sumMatrix[y][x-1] - sumMatrix[y-1][x-1]) + input;
				}
			}
			
			int result = Integer.MIN_VALUE;
			
			for (int i = 0 ; i <= (N-M) ; i ++) {
				for (int j = 0 ; j <= (N-M) ; j ++) {
					int x1 = j;
					int y1 = i;
					int x2 = x1+M;
					int y2 = y1+M;
					
					int sum = sumMatrix[x2][y2] - (sumMatrix[x1][y2] + sumMatrix[x2][y1] - sumMatrix[x1][y1]); 
					result = Math.max(sum, result);
				}
			}
			
			bw.write("#" + (testCase+1) + " "+ result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
