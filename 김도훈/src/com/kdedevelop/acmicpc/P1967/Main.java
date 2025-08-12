package com.kdedevelop.acmicpc.P1967;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int maxDistanceNode;
	static int maxDistance;
	static void dfs(Map<Integer, Integer>[] tree, boolean[] visited, int curr, int sumDistance) {
		if (maxDistance < sumDistance) {
			maxDistance = sumDistance;
			maxDistanceNode = curr;
		}
		if (visited[curr]) return;
		visited[curr] = true;
		Map<Integer, Integer> node = tree[curr];
		for (int child : node.keySet()) {
			if (visited[child]) continue;
			int distance = node.get(child);
			dfs(tree, visited, child, sumDistance + distance);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		Map<Integer, Integer>[] tree = new HashMap[N];
		Set<Integer> rootCounter = new HashSet<>();
		Set<Integer> childCounter = new HashSet<>();
		for (int i = 0 ; i < N ; i ++) {
			tree[i] = new HashMap<>();
		}
		
		for (int i = 0 ; i < N-1 ; i ++) {
			StringTokenizer edge = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(edge.nextToken())-1;
			int child = Integer.parseInt(edge.nextToken())-1;
			int distance = Integer.parseInt(edge.nextToken());
			tree[parent].put(child, distance);
			tree[child].put(parent, distance);
			childCounter.add(child);
			if (!childCounter.contains(parent)) rootCounter.add(parent);
			if (rootCounter.contains(child)) rootCounter.remove(child);
		}
		
		if (rootCounter.size() == 0) {
			bw.write(String.valueOf(0));
		} else {
			int rootIndex = rootCounter.iterator().next();
			
			maxDistance = 0;
			
			dfs(tree, new boolean[N], rootIndex, 0);
			dfs(tree, new boolean[N], maxDistanceNode, 0);
			
			bw.write(String.valueOf(maxDistance));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
