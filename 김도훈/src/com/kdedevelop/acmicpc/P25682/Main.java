package com.kdedevelop.acmicpc.P25682;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNMK = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(inputNMK.nextToken());
		int M = Integer.parseInt(inputNMK.nextToken());
		int K = Integer.parseInt(inputNMK.nextToken());
		
		boolean[][] board = new boolean[N][M];
		for (int i = 0 ; i < N ; i ++) {
			String inputLine = br.readLine();
			for (int j = 0 ; j < M ; j ++) {
				board[i][j] = inputLine.charAt(j) == 'W' ? true : false;
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int startColorType = 0 ; startColorType < 2 ; startColorType ++) {
			//0 이면 흰색, 1이면 검정으로 시작
			boolean[][] newBoard = new boolean[N+1][M+1];
			newBoard[0][0] = startColorType == 0 ? true : false;
			for (int i = 1 ; i <= N ; i ++) {
				newBoard[i][0] = !newBoard[i-1][0];
			}
			for (int i = 1 ; i <= M ; i ++) {
				newBoard[0][i] = !newBoard[0][i-1];
			}
			
			
			
			int[][] sumOfChangeBoard = new int[N+1][M+1];
			
			for (int i = 1 ; i <= N ; i ++) {
				for (int j = 1 ; j <= M ; j ++) {
					boolean currColor = board[i-1][j-1];
					boolean lastColor = newBoard[i-1][j-1];
					sumOfChangeBoard[i][j] = sumOfChangeBoard[i-1][j] + sumOfChangeBoard[i][j-1] - sumOfChangeBoard[i-1][j-1];
					if (lastColor != currColor) {
						currColor = !currColor;
						sumOfChangeBoard[i][j] ++;
					}
					newBoard[i][j] = currColor;
				}
			}
			
			for (int i = 0 ; i <= N-K ; i ++) {
				for (int j = 0 ; j <= M-K ; j ++) {
//					System.out.println("K, K : " + sumOfChangeBoard[i+K][j+K] + " || I K : " + sumOfChangeBoard[i][j+K] + " || K J : " + sumOfChangeBoard[i+K][j] + " || I J : " + sumOfChangeBoard[i][j] );
					int change = sumOfChangeBoard[i+K][j+K] - (sumOfChangeBoard[i][j+K] + sumOfChangeBoard[i+K][j] - sumOfChangeBoard[i][j]);
					result = Math.min(result, change);
				}
			}
			
//			for (int i = 0 ; i <= N ; i ++) {
//				for (int j = 0 ; j <= M ; j ++) {
////					System.out.print((newBoard[i][j] ? 'W' : 'B'));
//					System.out.print(String.format("%02d ", sumOfChangeBoard[i][j]));
//				}
//				System.out.println("");
//			}
			
		}
			
		bw.write(String.valueOf(result));
		
		
		bw.flush();
		bw.close();
		br.close();
	}

}
