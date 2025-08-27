package com.kdedevelop.swea.P2112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void printFilm(boolean[][] film) {
		for(int i = 0 ; i < D ; i ++) {
			for (int j = 0 ; j < W ; j ++) {
				System.out.print((film[i][j] ? "1" : "0"));
			}
			System.out.println("");
		}
		System.out.println("============");
	}
	
	public boolean isFilmAvailable(boolean[][] film) {
		for(int x = 0 ; x < W ; x ++) {
			if(!isLineAvailable(x, film)) return false;
		}
		return true;
	}
	
	public boolean isLineAvailable(int x, boolean[][] film) {
		boolean currType = FILM[0][x];
		int count = 1;
		
		for(int y = 1 ; y < D ; y ++) {
			boolean nextType = FILM[y][x];
			if(currType == nextType) ++ count;
			else {
				currType = nextType;
				count = 1;
			}
			
			if(count == K) return true;
		}
		
		return false;
	}
	
	boolean[] chemicalA;
	boolean[] chemicalB;
	
	boolean success;
	public void dfsLineChooser(int depth, int end, int start, boolean[][] film) {
		if(depth == end) {
			if(isFilmAvailable(film)) {
//				printFilm(film);
				success = true;
			}
		} else {
			for(int i = start + 1 ; i < D ; i ++) {
				if(success) return;
				
				boolean[] original = film[i];
				
				film[i] = chemicalA;
				dfsLineChooser(depth + 1, end, i, film);
				
				film[i] = chemicalB;
				dfsLineChooser(depth + 1, end, i, film);
				
				film[i] = original;
			}
		}
	}
	
	int result;
	int D;
	int W;
	int K;
	boolean[][] FILM;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputDWK = new StringTokenizer(br.readLine());
			D = Integer.parseInt(inputDWK.nextToken());
			W = Integer.parseInt(inputDWK.nextToken());
			K = Integer.parseInt(inputDWK.nextToken());
			success = false;
			FILM = new boolean[D][W];
			chemicalA = new boolean[W];
			chemicalB = new boolean[W];
			for(int i = 0 ; i < W ; i ++) {
				chemicalA[i] = true;
				chemicalB[i] = false;
			}
			for(int y = 0 ; y < D ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < W ; x ++) {
					FILM[y][x] = lineInput.nextToken().charAt(0) == '1';
				}
			}
			
			for(int i = 0 ; i < D ; i ++) {
				dfsLineChooser(0, i, -1, FILM);
				if(success) {
					result = i;
					break;
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
