package com.kdedevelop.acmicpc.P2577;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	long[] result;
	int A, B, C;
	public void solution() throws IOException {
		sb = new StringBuilder();
		result = new long[10];
		A = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		
		int multi = A * B * C;
		while(true) {
			if(multi == 0) break;
			
			int temp = multi % 10;
			++ result[temp];
			multi /= 10;
		}
		
		for(int i = 0 ; i < 10 ; i ++) {
			sb.append(result[i]).append("\n");
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
