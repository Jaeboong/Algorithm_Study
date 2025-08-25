package com.kdedevelop.acmicpc.P12851;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public boolean isOutOfField(int x) {
		if (x < 0 || x > 100000) return true;
		return false;
	}
	
	int N;
	int K;
	int[] time;
	int count;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		
		int result = Integer.MAX_VALUE;
		time = new int[100001];
		count = 0;
		for(int i = 0 ; i < 100001 ; i ++) time[i] = Integer.MAX_VALUE;
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {N, 0});
		while(true) {
//			System.out.println(result);
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			int currX = curr[0];
			int currTime = curr[1];
			int nextTime = currTime + 1;
			
			if(currX == K) {
				if(time[K] > currTime) {
					count = 0;
					time[K] = currTime;
					result = currTime;
				}
				if(time[K] == currTime) ++ count;
				continue;
			}
			
			int[] nextXArray = {currX - 1, currX + 1, currX * 2};
			for(int nextX : nextXArray) {
				if(isOutOfField(nextX)) continue;
				if(time[nextX] < nextTime) continue;
				
				time[nextX] = nextTime;
				queue.offer(new int[] {nextX, nextTime});
			}
		}
		
		
		bw.write(String.valueOf(time[K]) + "\n" + String.valueOf(count));
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}