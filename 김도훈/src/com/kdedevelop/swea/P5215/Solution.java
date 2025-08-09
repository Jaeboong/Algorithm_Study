package com.kdedevelop.swea.P5215;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Resource {
	public Resource(String input) {
		StringTokenizer st = new StringTokenizer(input);
		this.tast = Integer.parseInt(st.nextToken());
		this.cal = Integer.parseInt(st.nextToken());
	}
	public Resource(int tast, int cal) {
		this.tast = tast;
		this.cal = cal;
	}
	int tast;
	int cal;
}

public class Solution {
	static int result = Integer.MIN_VALUE;
	public static void dfs(Resource[] resources, int resourceCount, int limit, boolean[] visited, int start, int cal, int tast) {
		for (int i = start ; i < resourceCount ; i ++) {
			if (visited[i]) continue;
			
			Resource resource = resources[i];
			
			int sumCal = cal + resource.cal;
			int sumTast = tast + resource.tast;
			
			if (sumCal <= limit) {
				visited[i] = true;
				result = Math.max(result, sumTast);
				dfs(resources, resourceCount, limit, visited, i, cal+resource.cal, tast+resource.tast);
				visited[i] = false;
			} else {
				continue;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Integer.MIN_VALUE;
			StringTokenizer inputNL = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(inputNL.nextToken());
			int L = Integer.parseInt(inputNL.nextToken());
			
			Resource[] resources = new Resource[N];
			for (int i = 0 ; i < N ; i ++) {
				resources[i] = new Resource(br.readLine());
			}
			
			dfs(resources, N, L, new boolean[N], 0, 0, 0);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
