package com.kdedevelop.acmicpc.P2475;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long result;
	int N = 5;
	int[] NUMBERs;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		
		NUMBERs = new int[N];
		StringTokenizer inputNumbers = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			int value = Integer.parseInt(inputNumbers.nextToken());
			NUMBERs[i] = value;
			
			result = (result + ((value * value) % 10)) % 10;
		}
		
		sb.append(String.valueOf(result));
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
