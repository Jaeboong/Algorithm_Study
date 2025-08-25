package com.kdedevelop.swea.P7465;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int find(int x) {
		if(parent[x] != x) {
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
	
	int[] parent;
	int N;
	int M;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			StringTokenizer inputNM = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNM.nextToken());
			M = Integer.parseInt(inputNM.nextToken());
			parent = new int[N];
			for(int i = 0 ; i < N ; i ++) parent[i] = i;
			for(int i = 0 ; i < M ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(inputLine.nextToken())-1;
				int b = Integer.parseInt(inputLine.nextToken())-1;
				
				union(a, b);
			}
			
//			System.out.println(Arrays.toString(parent));
			
			Set<Integer> group = new HashSet<>();
			for(int groupHead : parent) group.add(find(groupHead));
			
			bw.write("#" + (testCase + 1) + " " + group.size() + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
