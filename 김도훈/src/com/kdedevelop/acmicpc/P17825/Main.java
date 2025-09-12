package com.kdedevelop.acmicpc.P17825;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int move(int curr, int count) {
		if(curr == 5) {
//			System.out.println("curve 1");
			curr = 22;
			-- count;
		}
		if(curr == 10) {
//			System.out.println("curve 2");
			curr = 25;
			-- count;
		}
		if(curr == 15) {
//			System.out.println("curve 3");
			curr = 27;
			-- count;
		}
		
		if(curr >= 22 && curr <= 24) {
//			System.out.println("curve 4");
			if(curr + count > 24) {
				count -= (25 - curr);
				curr = 30;
			} else {
				return curr + count;
			}
		}
		if(curr >= 25 && curr <= 26) {
//			System.out.println("curve 5");
			if(curr + count > 26) {
				count -= (27 - curr);
				curr = 30;
			} else {
				return curr + count;
			}
		}
		if(curr >= 27 && curr <= 29) {
//			System.out.println("curve 6");
			if(curr + count > 29) {
				count -= (30 - curr);
				curr = 30;
			} else {
				return curr + count;
			}
		}
		if(curr >= 30 && curr <= 32) {
//			System.out.println("curve 7");
			if(curr + count > 32) {
				count -= (33 - curr);
				curr = 20;
			} else {
				return curr + count;
			}
		}
		
		if(curr >= 16 && curr <= 20) {
//			System.out.println("curve 8");
			if(curr + count < 21) {
				return curr + count;
			} else {
				return 21;
			}
		}
		
		return curr + count;
	}
	
	public boolean test(int[] data) {
		if(data[0] != 0) return false;
		if(data[1] != 0) return false;
		if(data[2] != 1) return false;
		if(data[3] != 1) return false;
		if(data[4] != 1) return false;
		if(data[5] != 1) return false;
		if(data[6] != 1) return false;
		if(data[7] != 1) return false;
		if(data[8] != 1) return false;
		if(data[9] != 1) return false;
		return true;
	}
	
	int count = 0;
	public int getPoint(int[] data) {
//		if(test(data)) System.out.println(Arrays.toString(data));
		int point = 0;
		boolean[] boardPiece = new boolean[33];
		int[] PIECE = new int[4];
		for(int i = 0 ; i < 10 ; i ++) {
			int dice = DICE[i];
			int piece = data[i];
			
			int originalPiecePlace = PIECE[piece];
			
			if(originalPiecePlace == 21) return -1;
			
			int nextPiecePlace = move(originalPiecePlace, dice);
			
			if(boardPiece[nextPiecePlace]) return -1;
			
			if(originalPiecePlace != 0) boardPiece[originalPiecePlace] = false;
			if(nextPiecePlace != 21)    boardPiece[nextPiecePlace] = true;
			PIECE[piece] = nextPiecePlace;
			
			point += boardPoint[nextPiecePlace];
//			if(test(data)) System.out.println("DICE : " + dice + " || PIECE : " + piece + " || ORIGINAL : " + originalPiecePlace + " || NEXT : " + nextPiecePlace + " || POINT : " + point);
		}
		return point;
	}
	
	public void dfs(int depth, int[] data) {
		if(depth == 10) {
//			System.out.println(Arrays.toString(data));
//			++ count;
			
			int point = getPoint(data);
			
//			System.out.println(point);
			
			if(result < point) {
				result = point;
//				System.out.println(point);
//				System.out.println(Arrays.toString(data));
			}
			
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		} else {
			for(int i = 0 ; i < 4 ; i ++) {
				data[depth] = i;
				dfs(depth + 1, data);
			}
		}
	}
	
	int[] boardPoint = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, 13, 16, 19, 22, 24, 28, 27, 26, 25, 30, 35};
	int[] DICE;
	int result;
	public void solution() throws IOException {
		DICE = new int[10];
		result = 0;
		StringTokenizer inputPiece = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 10 ; i ++) {
			int value = Integer.parseInt(inputPiece.nextToken());
			DICE[i] = value;
		}
		
//		for(int i = 0 ; i < 33 ; i ++) {
//			System.out.println("I : " + i + " : " + boardPoint[i]);
//		}
		
		dfs(0, new int[10]);
//		System.out.println("SIZE : " + count);
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
