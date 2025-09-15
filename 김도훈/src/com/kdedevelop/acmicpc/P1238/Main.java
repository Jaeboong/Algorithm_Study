package com.kdedevelop.acmicpc.P1238;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N, M, X;
	List<int[]>[] goGraph;
	List<int[]>[] comeGraph;
	public void solution() throws IOException {
		StringTokenizer inputNMX = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNMX.nextToken());
		M = Integer.parseInt(inputNMX.nextToken());
		X = Integer.parseInt(inputNMX.nextToken());
		
		goGraph = new List[N];
		comeGraph = new List[N];
		for(int i = 0 ; i < N ; i ++) {
			goGraph[i] = new ArrayList<>();
			comeGraph[i] = new ArrayList<>();
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
