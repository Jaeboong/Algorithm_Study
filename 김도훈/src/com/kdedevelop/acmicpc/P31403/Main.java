package com.kdedevelop.acmicpc.P31403;

import java.io.*;
import java.util.*;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder sb;
	int A, B, C;
	public void solution() throws IOException {
		sb = new StringBuilder();
		
		A = Integer.parseInt(br.readLine());
		B = Integer.parseInt(br.readLine());
		C = Integer.parseInt(br.readLine());
		
		sb.append((A + B - C)).append("\n");
		sb.append((Integer.parseInt(String.valueOf(A) + String.valueOf(B)) - C));
		
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
