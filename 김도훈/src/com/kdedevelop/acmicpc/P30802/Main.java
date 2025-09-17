package com.kdedevelop.acmicpc.P30802;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long result;
	int N, T, P;
	int[] SIZE;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		N = Integer.parseInt(br.readLine());
		SIZE = new int[6];
		StringTokenizer inputSize = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 6 ; i ++) {
			int value = Integer.parseInt(inputSize.nextToken());
			SIZE[i] = value;
		}
		StringTokenizer inputTP = new StringTokenizer(br.readLine());
		T = Integer.parseInt(inputTP.nextToken());
		P = Integer.parseInt(inputTP.nextToken());
		
		int count = 0;
		for(int i = 0 ; i < 6 ; i ++) {
			count += SIZE[i] / T;
			if(SIZE[i] % T > 0) ++ count;
		}
		
		sb.append(String.valueOf(count)).append("\n").append((N / P)).append(" ").append((N % P));
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
