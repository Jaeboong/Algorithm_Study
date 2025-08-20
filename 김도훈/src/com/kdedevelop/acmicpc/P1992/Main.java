package com.kdedevelop.acmicpc.P1992;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean checkVideo(int startX, int startY, int endX, int endY) {
		boolean color = VIDEO[startY][startX];
		for (int y = startY ; y < endY ; y ++) {
			for (int x = startX ; x < endX ; x ++) {
				if (VIDEO[y][x] != color) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String func(int startX, int startY, int endX, int endY) {
		boolean color = VIDEO[startY][startX];
		boolean compressed = checkVideo(startX, startY, endX, endY);
		
		if (compressed) {
			return color ? "1" : "0";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			
			int middleX = (startX + ((endX - startX) / 2));
			int middleY = (startY + ((endY - startY) / 2));
			
			sb.append(func(startX, startY, middleX, middleY));
			sb.append(func(middleX, startY, endX, middleY));
			sb.append(func(startX, middleY, middleX, endY));
			sb.append(func(middleX, middleY, endX, endY));
		
			sb.append(")");
			return sb.toString();
		}
	}
	
	int N;
	boolean[][] VIDEO;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		VIDEO = new boolean[N][N];
		for (int i = 0 ; i < N ; i ++) {
			String inputLine = br.readLine();
			for (int j = 0 ; j < N ; j ++) {
				VIDEO[i][j] = inputLine.charAt(j) == '1' ? true : false;
			}
		}
		
		bw.write(func(0, 0, N, N));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
