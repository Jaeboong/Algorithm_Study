package com.kdedevelop.acmicpc.P10971;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	boolean[] visit;
	public void dfs(int start, int depth, int curr, int sumOfDistance) {
		if (sumOfDistance > result) return;
		
		
		if (depth == N - 1) {
//			System.out.println("DEPTH : " + depth + " || CURR : " + curr + " || DIS : " + sumOfDistance);
			int distance = INPUT[curr][start];
			if(distance == 0) return;
			int resultDistance = sumOfDistance + INPUT[curr][start];
			result = Math.min(resultDistance, result);
		} else {
			visit[curr] = true;
			for(int next = 0 ; next < N ; next ++) {
				if(next == curr) continue;
				if(visit[next]) continue;
				
				int distance = INPUT[curr][next];
				if (distance == 0) continue;
				
				dfs(start, depth + 1, next, sumOfDistance + distance);
			}
			visit[curr] = false;
		}
	}
	
	int N;
	int[][] INPUT;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		INPUT = new int[N][N];
		result = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < N ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j ++) {
				int tmp = Integer.parseInt(inputLine.nextToken());
				INPUT[i][j] = tmp;
			}
		}
		
//		System.out.println("============");
//		
//		for(int i = 0 ; i < N ; i ++) {
//			for(int j = 0 ; j < N ; j ++) {
//				System.out.print(INPUT[i][j] + " ");
//			}
//			System.out.println("");
//		}
		
		for(int i = 0 ; i < N ; i ++) {
//			System.out.println("============");
			visit = new boolean[N];
			dfs(i, 0, i, 0);
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
