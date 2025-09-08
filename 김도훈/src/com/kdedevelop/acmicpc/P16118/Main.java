package com.kdedevelop.acmicpc.P16118;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

enum Speed {
	FAST, SLOW;
	public static int convert(Speed speed) {
		switch(speed) {
			case FAST : return 0;
			case SLOW : return 1;
			default : return -1;
		}
	}
}

class Node implements Comparable<Node> {
	public Node(int number, long distance) {
		this.number = number;
		this.distance = distance;
	}
	public Node(int number, long distance, Speed speed) {
		this(number, distance);
		this.speed = speed;
	}
	
	int number;
	long distance;
	Speed speed;
	
	@Override
	public int compareTo(Node o) {
		return Long.compare(this.distance, o.distance);
	}
}

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public void dijkstraWolf() {
		Queue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(0, 0, Speed.FAST));
		distanceWolf[Speed.convert(Speed.FAST)][0] = 0;
		
		int count = 0;
		while(true) {
			if(queue.isEmpty()) break;
			if(count == N) break;
			
			Node curr = queue.poll();
			if(visitWolf[Speed.convert(curr.speed)][curr.number]) continue;
			visitWolf[Speed.convert(curr.speed)][curr.number] = true;
			
//				System.out.println("CURR : " + Arrays.toString(curr));
			for(int[] next : VERTEX[curr.number]) {
				Speed nextSpeed = curr.speed == Speed.FAST ? Speed.SLOW : Speed.FAST;
				if(visitWolf[Speed.convert(nextSpeed)][next[0]]) continue;
				
//				System.out.println("NEXT : " + Arrays.toString(next));
				long temp = curr.distance + (curr.speed == Speed.FAST ? next[1] * 1 : next[1] * 4);
				
//				System.out.println("TEMP : " + temp);
				
//				if(curr[0] == 2) {
//					System.out.println("CURR SPEED : " + curr[2] + " || NEXT SPEED : " + nextSpeed);
//					System.out.println(Arrays.toString(visitWolf[0]) + " || " + Arrays.toString(visitWolf[1]));
//					System.out.println(Arrays.toString(curr) + " || " + Arrays.toString(next) + " || TEMP : " + temp);
//				}
//				if(temp == 10) System.out.println(Arrays.toString(curr) + " || " + Arrays.toString(next));
				
				if(distanceWolf[Speed.convert(nextSpeed)][next[0]] > temp) {
					distanceWolf[Speed.convert(nextSpeed)][next[0]] = temp;
					queue.offer(new Node(next[0], temp, nextSpeed));
				}
//				System.out.println("FOX : 0 " + Arrays.toString(distanceWolf[0]));
//				System.out.println("FOX : 0 " + Arrays.toString(visitWolf[0]));
//				System.out.println("FOX : 1 " + Arrays.toString(distanceWolf[1]));
//				System.out.println("FOX : 1 " + Arrays.toString(visitWolf[1]));
//				
//				System.out.println("================");
				
			}
		}
	}
	
	public void dijkstraFox() {
		Queue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(0, 0));
		distanceFox[0] = 0;
		
		int count = 0;
		while(true) {
			if(queue.isEmpty()) break;
			if(count == N) break;
			
			Node curr = queue.poll();
			if(visitFox[curr.number]) continue;
			visitFox[curr.number] = true;
			
			for(int[] next : VERTEX[curr.number]) {
				if(visitFox[next[0]]) continue;
				
				long temp = curr.distance + next[1] * 2;
				if(distanceFox[next[0]] > temp) {
					distanceFox[next[0]] = temp;
					queue.offer(new Node(next[0], temp));
				}
			}
		}
	}
	
	int result;
	int N;
	int M;
	List<int[]>[] VERTEX;
	boolean[] visitFox;
	long[] distanceFox;
	boolean[][] visitWolf;
	long[][] distanceWolf;
	public void solution() throws IOException {
		StringTokenizer inputNM = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(inputNM.nextToken());
		M = Integer.parseInt(inputNM.nextToken());
		result = 0;
		visitFox = new boolean[N];
		distanceFox = new long[N];
		visitWolf = new boolean[2][N];
		distanceWolf = new long[2][N];
		VERTEX = new List[N];
		for(int i = 0 ; i < N ; i ++) {
			distanceFox[i] = Long.MAX_VALUE;
			distanceWolf[0][i] = Long.MAX_VALUE;
			distanceWolf[1][i] = Long.MAX_VALUE;
			VERTEX[i] = new ArrayList<>();
		}
		for(int i = 0 ; i < M ; i ++) {
			StringTokenizer inputLine = new StringTokenizer(br.readLine().trim());
			int u = Integer.parseInt(inputLine.nextToken()) - 1;
			int v = Integer.parseInt(inputLine.nextToken()) - 1;
			int w = Integer.parseInt(inputLine.nextToken());
			VERTEX[u].add(new int[] {v, w});
			VERTEX[v].add(new int[] {u, w});
		}
		
		dijkstraFox();
		dijkstraWolf();
//		System.out.println(Arrays.toString(distanceFox));
//		System.out.println(Arrays.toString(distanceWolf[0]));
//		System.out.println(Arrays.toString(distanceWolf[1]));
		
//		System.out.println(Arrays.toString(distance[0]));
//		System.out.println(Arrays.toString(distance[1]));
		
		for(int i = 0 ; i < N ; i ++) {
			if(distanceFox[i] < Math.min(distanceWolf[0][i], distanceWolf[1][i])) {
				++ result;
			}
		}
		
		bw.write(String.valueOf(result) + "\n");
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
