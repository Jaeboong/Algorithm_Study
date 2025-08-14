package com.kdedevelop.swea.P2805;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	int N;
	int[][] farm;
	void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			N = Integer.parseInt(br.readLine());
			farm = new int[N][N];
			
			for (int i = 0 ; i < N ; i ++) {
				String line = br.readLine();
				for (int j = 0 ; j < N ; j ++) {
					farm[i][j] = Integer.parseInt(line.charAt(j)+"");
				}
			}
			
			int middle = (N/2);
//			System.out.println("MIDDLE : " + middle);
			for (int i = 0, term = 1, start = N/2 ; i < middle ; i ++, term += 2, start --) {
				for (int j = 0 ; j < term ; j ++) {
//					System.out.println(i + " " + (start+j));
					result += farm[i][start+j];
				}
			}
			for (int i = 0 ; i < N ; i ++) {
//					System.out.println(middle + " " + (i));
				result += farm[middle][i];
			}
			for (int i = middle+1, term = N-2, start = 1 ; i < N ; i ++, term -= 2, start ++) {
				for (int j = 0 ; j < term ; j ++) {
//					System.out.println(start + " | " + j);
//					System.out.println(i + " " + (start+j));
					result += farm[i][start+j];
				}
			}
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
