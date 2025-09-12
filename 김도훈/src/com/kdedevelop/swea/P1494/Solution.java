package com.kdedevelop.swea.P1494;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void dfs(int depth, int choose, boolean[] used, long sumOfX, long sumOfY) {
		if(depth == N) {
			if(choose == groupCount) {
				long diffX = sumX - 2 * sumOfX;
				long diffY = sumY - 2 * sumOfY;
				long diff = diffX * diffX + diffY * diffY;
				result = Math.min(result, diff);
			}
		} else {
			used[depth] = true;
			dfs(depth + 1, choose + 1, used, sumOfX + WORMs[depth][0], sumOfY + WORMs[depth][1]);
			used[depth] = false;
			dfs(depth + 1, choose, used, sumOfX, sumOfY);
		}
	}
	
	long result;
	int N;
	int groupCount;
	long sumX, sumY;
	int[][] WORMs;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Long.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			sumX = 0;
			sumY = 0;
			groupCount = N / 2;
			WORMs = new int[N][];
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(lineInput.nextToken());
				int y = Integer.parseInt(lineInput.nextToken());
				sumX += x;
				sumY += y;
				int[] worm = new int[] {x, y};
				WORMs[i] = worm;
			}
			
			dfs(0, 0, new boolean[N], 0, 0);
			
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
