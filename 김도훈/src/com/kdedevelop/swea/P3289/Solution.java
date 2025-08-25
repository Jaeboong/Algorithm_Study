package com.kdedevelop.swea.P3289;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX != rootY) {
			parent[rootY] = rootX;
		}
	}
	
	public boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
	
	int N;
	int M;
	int[] parent;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			parent = new int[N];
			for(int i = 0 ; i < N ; i ++) parent[i] = i;
			
			
			bw.write("#" + (testCase + 1) + " ");
			for(int i = 0 ; i < M ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int operator = Integer.parseInt(inputLine.nextToken());
				int a = Integer.parseInt(inputLine.nextToken())-1;
				int b = Integer.parseInt(inputLine.nextToken())-1;
				
				switch (operator) {
					case 0 : union(a, b);
						break;
					case 1 : bw.write(isConnected(a, b) ? "1" : "0");
						break;
				}
			}
			bw.write("\n");
			
			
			
			
			
			
			
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
