package com.kdedevelop.acmicpc.P8958;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long result;
	public void solution() throws IOException {
		sb = new StringBuilder();
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			String input = br.readLine();
			int point = 0;
			int count = 0;
			for(int i = 0 ; i < input.length() ; i ++) {
				boolean value = input.charAt(i) == 'O';
				if(value) {
					++ count;
				} else {
					count = 0;
				}
				point += count;
			}
			result = point;
			sb.append(result).append("\n");
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
