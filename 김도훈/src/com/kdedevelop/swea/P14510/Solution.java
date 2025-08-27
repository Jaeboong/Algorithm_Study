package com.kdedevelop.swea.P14510;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	long result;
	int N;
	int[] HEIGHTs;
	int[] growHeights;
	int oddCount;
	int oddSum;
	int evenCount;
	int evenSum;
	int sumOfGrowHeight;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			HEIGHTs = new int[N];
			int maxHeight = Integer.MIN_VALUE;
			StringTokenizer inputHeight = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				int height = Integer.parseInt(inputHeight.nextToken());
				maxHeight = Math.max(maxHeight, height);
				HEIGHTs[i] = height;
			}
			growHeights = new int[N];
			
			oddCount = 0;
			oddSum = 0;
			evenCount = 0;
			evenSum = 0;
			sumOfGrowHeight = 0;
			for(int i = 0 ; i < N ; i ++) {
				int height = HEIGHTs[i];
				int diff = maxHeight - height;
				growHeights[i] = diff;
				if(diff % 2 == 0) {
					++ evenCount;
					evenSum += diff;
				} else {
					++ oddCount;
					oddSum += diff;
				}
				sumOfGrowHeight += diff;
			}
			
//			System.out.println("SUM OF GROW HEIGHT : " + sumOfGrowHeight);
			int minDay = (sumOfGrowHeight / 3) * 2 + sumOfGrowHeight % 3;
			int evenDayCount = minDay / 2;
			int oddDayCount = minDay / 2 + minDay % 2;
			
			if(oddDayCount >= oddCount) {
				result = minDay;
			} else {
				result = ((oddCount - 1) * 2) + 1;
			}
			
//			System.out.println("ODD COUNT : " + oddCount + " || SUM : " + oddSum + " || EVEN COUNT : " + evenCount + " || SUM : " + evenSum);
			
			
			bw.write("#" + (testCase + 1) + " " + result + "\n");
		}
			
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
