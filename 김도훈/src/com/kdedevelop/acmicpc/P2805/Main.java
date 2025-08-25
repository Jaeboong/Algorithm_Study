package com.kdedevelop.acmicpc.P2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N;
	long M;
	long[] TREEs;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		TREEs = new long[N];
		StringTokenizer inputTrees = new StringTokenizer(br.readLine());
		long maxTree = Long.MIN_VALUE;
		for(int i = 0 ; i < N ; i ++) {
			long tree = Long.parseLong(inputTrees.nextToken());
			if (maxTree < tree) maxTree = tree;
			TREEs[i] = tree;
		}
		
		long start = 1;
		long end = maxTree;
		long result = 0;
		while(true) {
			if(end < start) break;
			
			long middle = (start + end) / 2;
			
			long sum = 0;
			for(long tree : TREEs) {
				long diff = tree - middle;
				if (diff < 0) continue;
				sum += diff;
			}
			
//			System.out.println("START : " + start + " || END : " + end + " || MIDDLE : " + middle + " || SUM : " + sum);
			
			if (sum < M) {
				end = middle - 1;
			}
			
			if (sum >= M) {
				result = middle;
				start = middle + 1;
			}
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
