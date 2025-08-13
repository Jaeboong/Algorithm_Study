package com.kdedevelop.acmicpc.P14889;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static BufferedWriter bw;
	public static int result = Integer.MAX_VALUE;
	public static void dfs(int n, int r, int[][] team, int depth, int start, int[] data) {
		if (depth == r) {
			int diff = 0;
			int teamI = 0;
			int teamJ = 0;
			
			Set<Integer> set = new HashSet<>();
			for (int i = 0 ; i < n ; i ++) set.add(i);
			
			for (int i = 0 ; i < r ; i ++) {
				int worker = data[i];
				set.remove(worker);
				for (int j = 0 ; j < r ; j ++) {
					if (i == j) continue;
					int coWorker = data[j];
					teamI += team[worker][coWorker];
				}
			}
			
			for (int worker : set) {
				for (int coWorker : set) {
					if (worker == coWorker) continue;
					teamJ += team[coWorker][worker];
				}
			}
			
			diff = teamI - teamJ;
			if (diff < 0) diff *= -1;
			
			result = Math.min(diff, result);
		} else {
			for (int i = start ; i < n ; i ++) {
				data[depth] = i;
				dfs(n, r, team, depth+1, i+1, data);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[][] team = new int[N][N];
		
		for (int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < N ; j ++) {
				team[i][j] = Integer.parseInt(inputLine.nextToken());
			}
		}
		
		dfs(N, N/2, team, 0, 0, new int[N/2]);
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
