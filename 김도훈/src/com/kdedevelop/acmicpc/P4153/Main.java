package com.kdedevelop.acmicpc.P4153;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long result;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = 0;
		
		while(true) {
			StringTokenizer inputABC = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(inputABC.nextToken());
			int b = Integer.parseInt(inputABC.nextToken());
			int c = Integer.parseInt(inputABC.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			
			if(a * a + b * b == c * c) {
				sb.append("right").append("\n");
				continue;
			}
			if(a * a == b * b + c * c) {
				sb.append("right").append("\n");
				continue;
			}
			if(a * a + c * c == b * b) {
				sb.append("right").append("\n");
				continue;
			}
			sb.append("wrong").append("\n");
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
