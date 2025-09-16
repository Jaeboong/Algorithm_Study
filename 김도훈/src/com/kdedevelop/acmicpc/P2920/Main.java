package com.kdedevelop.acmicpc.P2920;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean isSame(int[] a, int[] b) {
		for(int i = 0 ; i < 8 ; i ++) {
			if(a[i] != b[i]) return false;
		}
		return true;
	}
	
	StringBuilder sb;
	long result;
	int[] input;
	int[] asce = {1, 2, 3, 4, 5, 6, 7, 8};
	int[] desc = {8, 7, 6, 5, 4, 3, 2, 1};
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		input = new int[8];
		
		StringTokenizer inputLine = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 8 ; i ++) {
			int value = Integer.parseInt(inputLine.nextToken());
			input[i] = value;
		}
		
		if(isSame(input, asce))      sb.append("ascending");
		else if(isSame(input, desc)) sb.append("descending");
		else                         sb.append("mixed");
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
