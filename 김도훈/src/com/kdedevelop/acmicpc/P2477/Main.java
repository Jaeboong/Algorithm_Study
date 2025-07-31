package com.kdedevelop.acmicpc.P2477;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("Test3.txt"));
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
			int Kcal = Integer.parseInt(br.readLine());
			
			int[] length = new int[6];
			int[] dist = new int[6];

			int largeSquareWidth = Integer.MIN_VALUE;
			int largeSquareWidthIndex = -1;
			
			int largeSquareHeight = Integer.MIN_VALUE;
			int largeSquareHeightIndex = -1;
			
			for (int i = 0 ; i < 6 ; i ++) {
				StringTokenizer input = new StringTokenizer(br.readLine(), " ");
				int dis = Integer.parseInt(input.nextToken());
				int len = Integer.parseInt(input.nextToken());
				length[i] = len;
				dist[i] = dis;
				
				if (dis == 1 || dis == 2) {
					if (largeSquareWidth < len) {
						largeSquareWidth = len;
						largeSquareWidthIndex = i;
					}
				}
				
				if (dis == 3 || dis == 4) {
					if (largeSquareHeight < len) {
						largeSquareHeight = len;
						largeSquareHeightIndex = i;
					}
				}
			}
			
			int largeSquare = largeSquareHeight * largeSquareWidth;
			
			int smallSquareWidth = largeSquareWidth - Math.min(length[(largeSquareHeightIndex + 6 - 1) % 6], length[(largeSquareHeightIndex + 6 + 1) % 6]);
			int smallSquareHeight = largeSquareHeight - Math.min(length[(largeSquareWidthIndex + 6 - 1) % 6], length[(largeSquareWidthIndex + 6 + 1) % 6]);
			
			int smallSquare = smallSquareHeight * smallSquareWidth;
			
			int house = largeSquare - smallSquare;
			int result = house * Kcal;
			
			bw.write(String.valueOf(result));
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException ignore) {}
	}
}
