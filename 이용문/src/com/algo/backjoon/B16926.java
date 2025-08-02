package com.algo.backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B16926 {
	static int[][] a;
	static int n, m, r;
	static int layer;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	public static void rotate(int layer) {
		int y = layer;
		int x = layer;
		int tmp = a[y][x];
		int idx = 0;
		while(idx < 4) {
			int ny = y + dy[idx];
			int nx = x + dx[idx];
			if(ny >= layer && nx >= layer && ny < n - layer && nx < m - layer) {
				a[y][x] = a[ny][nx];
				y = ny;
				x = nx;
			}
			else idx++;
		}
		a[layer + 1][layer] = tmp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		a = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		layer = Math.min(n, m) / 2;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < layer; j++) {
				rotate(j);
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}

}
