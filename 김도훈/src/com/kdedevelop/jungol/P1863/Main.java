package com.kdedevelop.jungol.P1863;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int find(int a) {
		if(parent[a] != a) parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public boolean union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		
		if(parentA == parentB) return false;
		
		parent[parentA] = parentB;
		return true;
	}
	
	int N;
	int M;
	int[] parent;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		parent = new int[N];
		int count = N;
		for(int i = 0 ; i < N ; i ++) parent[i] = i;
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(inputLine.nextToken())-1;
			int b = Integer.parseInt(inputLine.nextToken())-1;
			
			if(union(a, b)) -- count;
		}
		
		bw.write(String.valueOf(count));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
