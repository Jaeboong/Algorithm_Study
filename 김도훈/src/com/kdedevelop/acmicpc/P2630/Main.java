package com.kdedevelop.acmicpc.P2630;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int countWhite;
	int countBlue;
	public void function(int startX, int startY, int endX, int endY) {
//		System.out.println("START X : " + startX + " || START Y : " + startY + " || END X : " + endX + " || END Y  : " + endY);
		boolean color = PAPER[startY][startX];
		boolean isPaper = true;
		for (int y = startY ; y < endY ; y ++) {
			for (int x = startX ; x < endX ; x ++) {
				if (PAPER[y][x] != color) {
					isPaper = false;
					continue;
				}
			}
		}
		if (isPaper) {
			if (color) countBlue++;
			else countWhite++;
		} else {
			int middleX = (startX + ((endX - startX) / 2));
			int middleY = (startY + ((endY - startY) / 2));
			function(startX, startY, middleX, middleY);
			function(middleX, startY, endX, middleY);
			function(startX, middleY, middleX, endY);
			function(middleX, middleY, endX, endY);
		}
	}
	
	int N;
	boolean[][] PAPER;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		PAPER = new boolean[N][N];
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int j =0 ; j < N ; j ++) {
				if (inputLine.nextToken().equals("1")) PAPER[i][j] = true;
			}
		}
		countWhite = 0;
		countBlue = 0;
		
		function(0, 0, N, N);
		
		bw.write(String.valueOf(countWhite) + "\n");
		bw.write(String.valueOf(countBlue) + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
