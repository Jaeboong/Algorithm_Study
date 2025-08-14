package com.kdedevelop.swea.P5215.HW20250813;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Food {
	public Food(String input) {
		StringTokenizer st = new StringTokenizer(input);
		this.tast = Integer.parseInt(st.nextToken());
		this.cal = Integer.parseInt(st.nextToken());
	}
	int tast;
	int cal;
}

public class Solution {
	static int result = 0;
	public static void dfs(int start, int currentCal, int currentTast) {
		result = Math.max(result, currentTast);
		for (int i = start ; i < N ; i ++) {
			Food food = foods[i];
			int sumOfCal = currentCal + food.cal;
			int sumOfTast = currentTast + food.tast;
			if (sumOfCal > L) continue;
			dfs(i+1, sumOfCal, sumOfTast);
		}
	}
	
	static int N;
	static int L;
	static Food[] foods;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNL = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNL.nextToken());
			L = Integer.parseInt(inputNL.nextToken());
			foods = new Food[N];
			for (int i = 0 ; i < N ; i++) foods[i] = new Food(br.readLine());
			
			dfs(0, 0, 0);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
