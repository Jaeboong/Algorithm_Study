package com.kdedevelop.acmicpc.P2741;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long result;
	int N;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		N = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < N ; i ++) {
			sb.append((i + 1)).append("\n");
		}
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
