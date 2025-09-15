package com.kdedevelop.acmicpc.P9205;

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
	
	public int getDistance(int[] a, int[] b) {
		int distX = a[0] - b[0];
		if(distX < 0) distX *= -1;
		int distY = a[1] - b[1];
		if(distY < 0) distY *= -1;
		return distX + distY;
	}
	
	public int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		boolean[] visit = new boolean[N];
		queue.offer(HOUSE);
		
		while(true) {
			if(queue.isEmpty()) break;
			
			int[] curr = queue.poll();
			
//			System.out.println("FES DIST : " + getDistance(curr, FESTIBAL));
			if(getDistance(curr, FESTIBAL) <= 1000) return 1;
			
			for(int i = 0 ; i < N ; i ++) {
				if(visit[i]) continue;
				
				int[] conv = CONVINI[i];
				
				int dist = getDistance(curr, conv);
//				System.out.println("DIST : " + dist);
				
				if(dist > 1000) continue;
				
				queue.offer(conv);
				visit[i] = true;
			}
		}
		return -1;
	}
	
	int N;
	int[][] CONVINI;
	int[] HOUSE;
	int[] FESTIBAL;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			N = Integer.parseInt(br.readLine());
			CONVINI = new int[N][];
			
			StringTokenizer inputHouse = new StringTokenizer(br.readLine());
			int houseX = Integer.parseInt(inputHouse.nextToken());
			int houseY = Integer.parseInt(inputHouse.nextToken());
			HOUSE = new int[] {houseX, houseY};
		
			for(int i = 0 ; i < N ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(inputLine.nextToken());
				int y = Integer.parseInt(inputLine.nextToken());
				int[] conv = new int[] {x, y};
				CONVINI[i] = conv;
			}
			
			StringTokenizer inputFestibal = new StringTokenizer(br.readLine());
			int festibalX = Integer.parseInt(inputFestibal.nextToken());
			int festibalY = Integer.parseInt(inputFestibal.nextToken());
			FESTIBAL = new int[] {festibalX, festibalY};
			
			int result = bfs();
			bw.write((result == 1 ? "happy" : "sad") + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
