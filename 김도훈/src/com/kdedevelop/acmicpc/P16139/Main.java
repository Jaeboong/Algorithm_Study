package com.kdedevelop.acmicpc.P16139;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input = br.readLine();
		int[][] counter = new int[input.length()+1][26];
		for (int i = 0 ; i < input.length() ; i ++) {
			for (int j = 0 ; j < 26 ; j ++) {
				counter[i+1][j] = counter[i][j];
			}
			char character = input.charAt(i);
			counter[i+1][character - 'a'] ++;
		}
		
//		for (int[] countArray : counter) {
//			for (int count : countArray) {
//				System.out.print(count + " ");
//			}
//			System.out.println("");
//		}
		
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0 ; i < Q ; i ++) {
			StringTokenizer question = new StringTokenizer(br.readLine());
			char character = question.nextToken().charAt(0);
			int start = Integer.parseInt(question.nextToken());
			int end = Integer.parseInt(question.nextToken())+1;
			
			bw.write(String.valueOf(counter[end][character - 'a'] - counter[start][character - 'a']) + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}

}
