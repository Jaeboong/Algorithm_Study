package com.kdedevelop.jungol.P1183;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int W;
	int[] COIN = new int[6];
	int[] COIN_VALUE = {500, 100, 50, 10, 5, 1};
	int[] COIN_COUNT = new int[6];
	int TOTAL_COIN_COUNT;
	int SUM_COIN_VALUE;
	public void solution() throws IOException {
		W = Integer.parseInt(br.readLine());
		SUM_COIN_VALUE = 0;
		TOTAL_COIN_COUNT = 0;
		StringTokenizer inputCoin = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < 6 ; i ++) {
			COIN[i] = Integer.parseInt(inputCoin.nextToken());
			TOTAL_COIN_COUNT += COIN[i];
			SUM_COIN_VALUE += COIN[i] * COIN_VALUE[i];
		}
		
		int usedCoinCount = 0;
		int remain = SUM_COIN_VALUE - W;
		for (int i = 0 ; i < 6 ; i ++) {
//			System.out.println("REMAIN : " + remain + " || COIN VALUE : " + COIN_VALUE[i] + " || COIN : " + COIN[i] + " || COIN COUNT : " + COIN_COUNT[i]);
			while (true) {
				if (COIN[i] - COIN_COUNT[i] == 0) break;
				if (remain < COIN_VALUE[i]) break;
				remain -= COIN_VALUE[i];
				COIN_COUNT[i] ++;
				usedCoinCount ++;
			}
		}		
		
		bw.write(String.valueOf(TOTAL_COIN_COUNT - usedCoinCount) + "\n");
		for (int i = 0 ; i < 6 ; i ++) {
			bw.write(String.valueOf(COIN[i] - COIN_COUNT[i]) + " ");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
