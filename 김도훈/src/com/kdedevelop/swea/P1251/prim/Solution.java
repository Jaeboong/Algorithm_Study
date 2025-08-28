package com.kdedevelop.swea.P1251.prim;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import org.w3c.dom.ls.LSOutput;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public long getWeight(int[] node1, int[] node2) {
		long diffX = node1[0] - node2[0];
		if(diffX < 0) diffX *= -1;
		long diffY = node1[1] - node2[1];
		if(diffY < 0) diffY *= -1;
		return (diffX * diffX) + (diffY * diffY);
	}
	
	public boolean put(int node) {
		if(visit[node]) return false;
		visit[node] = true;
		
		for(long[] edge : NODEs[node]) {
			if(visit[(int) edge[0]]) continue;
			queue.offer(edge);
		}
		
		return true;
	}
	
	long result;
	int N;
	int[][] ISLANDs;
	List<long[]>[] NODEs;
	double E;
	
	boolean[] visit;
	Queue<long[]> queue;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			ISLANDs = new int[N][];
			NODEs = new List[N];
			StringTokenizer inputX = new StringTokenizer(br.readLine());
			StringTokenizer inputY = new StringTokenizer(br.readLine());
			E = Double.parseDouble(br.readLine());
			for(int i = 0 ; i < N ; i ++) {
				NODEs[i] = new ArrayList<>();
				int x = Integer.parseInt(inputX.nextToken());
				int y = Integer.parseInt(inputY.nextToken());
				int[] xy = {x, y};
				ISLANDs[i] = xy;
			}
			
			for(int i = 0 ; i < N ; i ++) {
				int[] islandA = ISLANDs[i];
				List<long[]> node = NODEs[i];
				for(int j = 0 ; j < N ; j ++) {
					if(i == j) continue;
					int[] islandB = ISLANDs[j];
					long weight = getWeight(islandA, islandB);
//					System.out.println("WEIGHT : " + weight);
					node.add(new long[] {j, weight});
				}
			}
			
//			for(List<long[]> temp : NODEs) {
//				System.out.println("====================");
//				for(long[] edge : temp) {
//					System.out.println(Arrays.toString(edge));
//				}
//				System.out.println("====================");
//			}
			
			visit = new boolean[N];
			queue = new PriorityQueue<>((e1, e2) -> Long.compare(e1[1], e2[1]));
			
			put(0);
			
//			for(long[] temp : queue) {
//				System.out.println(Arrays.toString(temp));
//			}
			
			int count = 1;
			long totalWeight = 0;
			while(true) {
				if(queue.isEmpty()) break;
				if(count == N) break;
				
				long[] curr = queue.poll();
				if(put((int) curr[0])) {
					++ count;
					totalWeight += curr[1];
				}
			}
			
			result = Math.round(E * totalWeight);
			bw.write("#" + (testCase + 1) + " " + Math.round(E * totalWeight) + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
