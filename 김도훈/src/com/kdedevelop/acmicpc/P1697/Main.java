package com.kdedevelop.acmicpc.P1697;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Info {
	public Info(int x, int time) {
		this.x = x;
		this.time = time;
	}
	int x;
	int time;
}

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean isOutOfField(Info info) {
		if (info.x < 0 || info.x > 100000) return true;
		return false;
	}
	
	public boolean isAvailable(Info curr, Info next) {
		return !isOutOfField(next) && !visited[next.x] && curr.x != next.x;
	}
	
	int N;
	int K;
	boolean[] visited;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		
		int result = Integer.MAX_VALUE;
		visited = new boolean[100001];
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(N, 0));
		while (true) {
			if (queue.isEmpty()) break;
			
			Info curr = queue.poll();
			if (visited[curr.x]) continue;
			visited[curr.x] = true;
			if (curr.x == K) {
				result = Math.min(result, curr.time);
				break;
			}
			
			Info minus = new Info(curr.x - 1, curr.time + 1);
			if (isAvailable(curr, minus)) {
//				visited[minus.x] = true;
				queue.add(minus);
			}
			
			Info plus = new Info(curr.x + 1, curr.time + 1);
			if (isAvailable(curr, plus)) {
//				visited[plus.x] = true;
				queue.add(plus);
			}
			
			Info multiple = new Info(curr.x * 2, curr.time + 1);
			if (isAvailable(curr, multiple)) {
//				visited[multiple.x] = true;
				queue.add(multiple);
			}
		}
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
