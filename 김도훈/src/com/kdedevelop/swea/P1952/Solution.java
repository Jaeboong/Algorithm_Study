package com.kdedevelop.swea.P1952;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	int[] payment = new int[4];
	int[] monthPlan = new int[12];
	
	boolean end;
	void dfs(int month, int price) {
		if (result < price) return;
		if (month >= 12) {
			result = Math.min(result, price);
			return;
		}
		
		dfs(month+12, price+payment[3]);
		dfs(month+3, price+payment[2]);
		dfs(month+1, price+payment[1]);
		dfs(month+1, price+(payment[0]*monthPlan[month]));
	}
	
	void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			end = false;
			result = Integer.MAX_VALUE;
			StringTokenizer inputPayment = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < 4 ; i ++) payment[i] = Integer.parseInt(inputPayment.nextToken());
			StringTokenizer inputMonthPlan = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < 12 ; i ++) monthPlan[i] = Integer.parseInt(inputMonthPlan.nextToken());
			
			dfs(0, 0);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
