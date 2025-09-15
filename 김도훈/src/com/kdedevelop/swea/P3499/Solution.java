package com.kdedevelop.swea.P3499;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	StringBuilder result;
	int N;
	String[] CARDs;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			CARDs = new String[N];
			StringTokenizer inputCards = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				String card = inputCards.nextToken();
				CARDs[i] = card;
			}
			
			int half = N / 2;
			int[] start = new int[] {0, half};
			if(N % 2 != 0) ++ start[1];
			for(int i = 0 ; i < half ; i ++) {
				for(int j = 0 ; j < 2 ; j ++) {
					result.append(CARDs[start[j] + i]).append(" ");
				}
			}
			if(N % 2 != 0) {
				result.append(CARDs[start[0] + half]);
			}
			
			bw.write("#" + (testCase + 1) + " " + result.toString() + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
