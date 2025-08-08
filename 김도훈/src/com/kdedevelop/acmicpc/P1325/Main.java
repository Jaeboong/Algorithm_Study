package com.kdedevelop.acmicpc.P1325;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static void work(int N, int node, int[] result, List<Integer>[] inputData) {
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(node);
		visited[node] = true;
		
		while (true) {
			if (queue.isEmpty()) break;
			int num = queue.poll();
			
			for (int temp : inputData[num]) {
				if (visited[temp]) continue;
				result[temp] ++;
				visited[temp] = true;
				queue.add(temp);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		List<Integer>[] inputData = new List[N];
		for (int i = 0 ; i < N ; i ++) {
			inputData[i] = new ArrayList<>();
		}
		
		for (int i = 0 ; i < M ; i ++) {
			StringTokenizer inputAB = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(inputAB.nextToken())-1;
			int B = Integer.parseInt(inputAB.nextToken())-1;
			
			inputData[A].add(B);
		}
		
		int[] result = new int[N];
		for (int i = 0 ; i < N ; i ++) {
			work(N, i, result, inputData);
		}
		
		int max = Integer.MIN_VALUE;
		for (int i : result) {
			max = Math.max(max, i);
		}
		
		Map<Integer, Set<Integer>> resultMap = new TreeMap<Integer, Set<Integer>>(Collections.reverseOrder());
		for (int i = 0 ; i < N ; i ++) {
			int sum = result[i];
			if (!resultMap.containsKey(sum)) resultMap.put(sum, new TreeSet<>());
			resultMap.get(sum).add(i);
		}
		
		for (int i : resultMap.get(resultMap.keySet().iterator().next())) {
			bw.write(String.valueOf(i+1) + " ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
