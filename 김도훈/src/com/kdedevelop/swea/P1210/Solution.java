package com.kdedevelop.swea.P1210;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	
	public static boolean isOutOfLadder(int x, int y) {
		return (x >= 100 || y >= 100 || x < 0 || y < 0);
	}
	
	static int[] disX = {-1, 1, 0};
	static int[] disY = {0, 0, -1};
	
	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\input (2).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = 10;
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			br.readLine();
			boolean[][] ladder = new boolean[100][100];
			int startX = 0;
			int startY = 0;
			for (int lineNumber = 0 ; lineNumber < 100 ; lineNumber ++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int charNumber = 0 ; charNumber < 100 ; charNumber ++) {
					String str = st.nextToken();
					switch (str) {
						case "0" : ladder[lineNumber][charNumber] = false;
							break;
						case "2" : startX = charNumber; startY = lineNumber;
						case "1" : ladder[lineNumber][charNumber] = true;
							break;
					}
				}
			}
			
			int result = 0;
			
			int currentX = startX;
			int currentY = startY;
			while (true) {
				if (currentY == 0) {
					result = currentX;
					break;
				}
				
				for (int dis = 0 ; dis < 2 ; dis ++) {
					int nextX = currentX + disX[dis];
					int nextY = currentY + disY[dis];
					
					if (isOutOfLadder(nextX, nextY)) {
						continue;
					}
					
					if (ladder[nextY][nextX]) {
						while (true) {
							nextX = nextX + disX[dis];
							nextY = nextY + disY[dis];
					
							if (isOutOfLadder(nextX, nextY) || !ladder[nextY][nextX]) {
								nextX = nextX - disX[dis];
								nextY = nextY - disY[dis];
								break;
							}
						}
						currentX = nextX;
						currentY = nextY;
						
						break;
					}
				}
				currentX += disX[2];
				currentY += disY[2];
			}
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
