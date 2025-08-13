package com.kdedevelop.acmicpc.P9663;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static int result = 0;
	
	public static boolean isCanPlace(int place, int[] placed, int depth) {
		boolean result = true;
		for (int i = 0 ; i < depth ; i ++) {
			if (placed[i] == place) result = false;
			int depthDiff = depth - i;
			int diff = placed[i] - place;
			if (diff < 0) diff *= -1;
			if (diff == depthDiff) result = false;
		}
		
//		System.out.println("PLACE : " + place + " || PLACED : " + Arrays.toString(placed) + " || DEPTH : " + depth + " || RESULT : " + result);
		return result;
	}
	
	public static void function(int N, int depth, int[] placed) {
		if (depth == N) {
//			System.out.println(Arrays.toString(placed));
			result ++;
		} else {
			for (int j = 0 ; j < N ; j ++) {
				if (isCanPlace(j, placed, depth)) {
					placed[depth] = j;
					function(N, depth+1, placed);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		function(N, 0, new int[N]);
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
