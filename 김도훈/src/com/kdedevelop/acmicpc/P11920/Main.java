package com.kdedevelop.acmicpc.P11920;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int N, K;
	int[] NUMBERs;
	public void solution() throws IOException {
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNK.nextToken());
		K = Integer.parseInt(inputNK.nextToken());
		NUMBERs = new int[N];
		StringTokenizer inputNumber = new StringTokenizer(br.readLine());
		Queue<Integer> reversQueue = new PriorityQueue<>(Collections.reverseOrder());
		ArrayDeque<Integer> stack = new ArrayDeque<>(N);
		for(int i = 0 ; i < N ; i ++) {
			int value = Integer.parseInt(inputNumber.nextToken());
			reversQueue.offer(value);
			stack.push(value);
			NUMBERs[i] = value;
		}
		
		Queue<Integer> queue = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0 ; i < N ; i ++) {
			int value = NUMBERs[i];
			
			if(queue.size() < K) {
				queue.offer(value);
			} else {
				if(queue.peek() > value) {
					sb.append(String.valueOf(value)).append(" ");
				} else {
					queue.offer(value);
					sb.append(String.valueOf(queue.poll())).append(" ");
				}
			}
		}
		
		while(true) {
			if(queue.isEmpty()) break;
			
			sb.append(String.valueOf(queue.poll())).append(" ");
		}
		
//		System.out.println(Arrays.toString(queue.toArray()));
//		
//		while(true) {
//			if(queue.isEmpty()) break;
//			
//			System.out.println(queue.poll());
//		}
		
		bw.write(sb.toString());
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
