package com.kdedevelop.swea.P1238;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Call {
	public Call(int number, int count) {
		this.number = number;
		this.count = count;
	}
	int number;
	int count;
}

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	int N;
	int START;
	
	List<Integer>[] graph = new List[100];
	public void solution() throws IOException {
		
		for (int testCase = 0 ; testCase < 10 ; testCase ++) {
			for (int i = 0 ; i < 100 ; i ++) {
				graph[i] = new LinkedList<>();
			}
			result = 0;
			StringTokenizer inputNS = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputNS.nextToken());
			START = Integer.parseInt(inputNS.nextToken())-1;
			StringTokenizer inputData = new StringTokenizer(br.readLine());
			for (int i = 0 ; i < N/2 ; i ++) {
				int from = Integer.parseInt(inputData.nextToken())-1;
				int to = Integer.parseInt(inputData.nextToken())-1;
				graph[from].add(to);
			}
			
			int maxCounter = -1;
			int maxNumber = 0;
			boolean[] visited = new boolean[100];
			Queue<Call> queue = new LinkedList<>();
			queue.add(new Call(START, 0));
			while (true) {
				if (queue.isEmpty()) break;
				
				Call current = queue.poll();
				if (visited[current.number]) continue;
				visited[current.number] = true;
				
				if (current.count > maxCounter)  {
					maxCounter = current.count;
					maxNumber = 0;
				}
				
				if (current.count == maxCounter) {
					maxNumber = Math.max(maxNumber, current.number);
				}
				
				for (int next : graph[current.number]) {
					if (visited[next]) continue;
					queue.add(new Call(next, current.count+1));
				}
				
				result = maxNumber;
			}
			
			bw.write("#" + (testCase + 1) + " " + (result+1) + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

}
