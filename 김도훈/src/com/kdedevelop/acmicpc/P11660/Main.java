package com.kdedevelop.acmicpc.P11660;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stNM = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stNM.nextToken());
		int M = Integer.parseInt(stNM.nextToken());
		
		int[][] matrix = new int[N+1][N+1];
		int[][] sumMatrix = new int[N+1][N+1];
		
		for (int i = 1 ; i <= N ; i ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for (int j = 1 ; j <= N ; j ++) {
				int input = Integer.parseInt(lineInput.nextToken());
				sumMatrix[i][j] = sumMatrix[i-1][j] + sumMatrix[i][j-1] - sumMatrix[i-1][j-1] + input;
				matrix[i][j] = input;
			}
		}
		
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer input = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(input.nextToken())-1;
			int y1 = Integer.parseInt(input.nextToken())-1;
			int x2 = Integer.parseInt(input.nextToken());
			int y2 = Integer.parseInt(input.nextToken());
			
			//System.out.println("X1 : " +x1 + " || Y1 : " +y1 + " || X2 : " +x2 + " || Y2 : " + y2);
			
			bw.write(sumMatrix[x2][y2] - (sumMatrix[x1][y2] + sumMatrix[x2][y1] - sumMatrix[x1][y1]) + "\n");
		}
		
		/*
		for (int i = 1 ; i <= N ; i ++) {
			for (int j = 1 ; j <= N ; j ++) {
				bw.write(sumMatrix[i][j] + " ");
			}
			bw.write("\n");
		}
		*/
		
		bw.flush();
		bw.close();
		br.close();
	}
}
