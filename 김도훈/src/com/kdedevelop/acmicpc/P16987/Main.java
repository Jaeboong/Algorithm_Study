package com.kdedevelop.acmicpc.P16987;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
class Egg {
	public Egg(String input) {
		StringTokenizer st = new StringTokenizer(input);
		this.durable = Integer.parseInt(st.nextToken());
		this.weight = Integer.parseInt(st.nextToken());
	}
	public Egg(Egg egg) {
		this.durable = egg.durable;
		this.weight = egg.weight;
	}
	int durable;
	int weight;
}
public class Main {
	static int result = 0;
	public static void dfs(int depth, int[] data) {
		if (depth == N) {
			Egg[] currEggs = new Egg[N];
			for (int i = 0 ; i < N ; i++) {
				if (currEggs[i] == null) currEggs[i] = new Egg(eggs[i]);
				if (currEggs[data[i]] == null) currEggs[data[i]] = new Egg(eggs[data[i]]);
				
				if (data[i] == i) return;
				if (currEggs[i].durable <= 0) continue;
				if (currEggs[data[i]].durable <= 0) continue;
				
//				System.out.println("I : " + i + " || DATA[I] : " + data[i]);
				
				currEggs[i].durable -= currEggs[data[i]].weight;
				currEggs[data[i]].durable -= currEggs[i].weight;
			}
			
			int count = 0;
			for (Egg egg : currEggs) if (egg.durable <= 0) count ++;
			
			if (result < count) {
//				System.out.println(Arrays.toString(data));
				result = count;
			}
		} else {
			for (int i = 0 ; i < N ; i++) {
				data[depth] = i;
				dfs(depth+1, data);
			}
		}
	}
	
	static int N;
	static Egg[] eggs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		for (int i = 0 ; i < N ; i ++) eggs[i] = new Egg(br.readLine());
		
		dfs(0, new int[N]);
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
