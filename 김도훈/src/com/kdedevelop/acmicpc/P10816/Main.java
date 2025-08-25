package com.kdedevelop.acmicpc.P10816;

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
	
	public int getUpperBoundIndex(int target) {
		int start = 0;
		int end = N;
		while(true) {
			if (end-start <= 0) break;
			
			int middle = (start + end) / 2;
			
			if (CARDs[middle] <= target) {
				start = middle + 1;
			}
			
			if (CARDs[middle] > target) {
				end = middle;
			}
		}
		return end + 1;
	}
	
	public int getLowerBoundIndex(int target) {
		int start = 0;
		int end = N;
		while(true) {
			if(end-start <= 0) break;
			
			int middle = (start + end) / 2;
			
			if (CARDs[middle] < target) {
				start = middle+1;
			}
			
			if (CARDs[middle] >= target) {
				end = middle;
			}
		}
		return end+1;
	}
	
	int N;
	int[] CARDs;
	int M;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		CARDs = new int[N];
		StringTokenizer inputCards = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int card = Integer.parseInt(inputCards.nextToken());
			CARDs[i] = card;
		}
		
		Arrays.sort(CARDs);
		
		M = Integer.parseInt(br.readLine());
		StringTokenizer inputMs = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i ++) {
			int target = Integer.parseInt(inputMs.nextToken());
			int targetCount = getUpperBoundIndex(target) - getLowerBoundIndex(target);
			bw.write(String.valueOf(targetCount) + " ");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
