package com.kdedevelop.acmicpc.P2655;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Brick {
	int size, height, weight, index;

	public Brick(int size, int height, int weight, int index) {
		this.size = size;
		this.height = height;
		this.weight = weight;
		this.index = index;
	}
}

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int COUNT;
	Brick[] BRICKs;
	public void solution() throws IOException {
		COUNT = Integer.parseInt(br.readLine());
		BRICKs = new Brick[COUNT];
		for(int i = 0 ; i < COUNT ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(inputLine.nextToken());
			int height = Integer.parseInt(inputLine.nextToken());
			int weight = Integer.parseInt(inputLine.nextToken());
			
			Brick brick = new Brick(size, height, weight, i);
			BRICKs[i] = brick;
		}
		
		Arrays.sort(BRICKs, new Comparator<Brick>() {
			@Override
			public int compare(Brick o1, Brick o2) {
				return Integer.compare(o1.size, o2.size) * -1;
			}
		});
		
		int[][] dp = new int[2][COUNT];
		Arrays.fill(dp[1], -1);
		
		int maxHeight = 0;
		int maxHeightIndex = 0;
		for(int i = 0 ; i < COUNT ; i ++) {
			Brick a = BRICKs[i];
			dp[0][i] = BRICKs[i].height;
			for(int j = 0 ; j < i ; j ++) {
				Brick b = BRICKs[j];
				if(b.size > a.size && b.weight > a.weight) {
					if(dp[0][j] + a.height > dp[0][i]) {
						dp[0][i] = dp[0][j] + a.height;
						dp[1][i] = j;
					}
				}
			}
			if(dp[0][i] > maxHeight) {
				maxHeight = dp[0][i];
				maxHeightIndex = i;
			}
		}
		
//		for(int i = 0 ; i < COUNT ; i ++) {
//			System.out.print(String.format("%3d", dp[0][i]));
//		}
//		System.out.println("");
//		for(int i = 0 ; i < COUNT ; i ++) {
//			System.out.print(String.format("%3d", dp[1][i]));
//		}
		
		ArrayList<Integer> result = new ArrayList<>();
		int curr = maxHeightIndex;
		while(true) {
			if(curr == -1) break;
			result.add(BRICKs[curr].index);
			curr = dp[1][curr];
		}
		
		int resultSize = result.size();
		bw.write(String.valueOf(resultSize));
		bw.write("\n");
		for(int i = 0 ; i < resultSize ; i ++) {
			bw.write(String.valueOf(result.get(i) + 1));
			bw.write("\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}