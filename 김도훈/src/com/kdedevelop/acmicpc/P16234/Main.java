package com.kdedevelop.acmicpc.P16234;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {0, 0, -1, 1};
	int[] dirY = {-1, 1, 0, 0};
	
	public boolean isOutOfWorld(int x, int y) {
		if(x < 0 || x >= N) return true;
		if(y < 0 || y >= N) return true;
		return false;
	}
	
	public int convert(int x, int y) {
		return (y * N) + x;
	}
	
	public boolean bfs() {
		boolean move = false;
		Map<Integer, List<int[]>> mergeGroup = new HashMap<>();
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		for(int y = 0 ; y < N ; y ++) {
			for(int x = 0 ; x < N ; x ++) {
				if(visit[y][x]) continue;
				
				int[] xy = {x, y};
				
				int comp = convert(x, y);
				mergeGroup.put(comp, new LinkedList<>());
				mergeGroup.get(comp).add(xy);
				
				queue.add(xy);
				visit[y][x] = true;
				
				while(true) {
					if(queue.isEmpty()) break;
					
					int[] curr = queue.poll();
					int currX = curr[0];
					int currY = curr[1];
					int currPopulation = WORLD[currY][currX];
					
					for(int dir = 0 ; dir < 4 ; dir ++) {
						int nextX = currX + dirX[dir];
						int nextY = currY + dirY[dir];
						
						if(isOutOfWorld(nextX, nextY)) continue;
						if(visit[nextY][nextX]) continue;
						
						int[] nextXY = {nextX, nextY};
						
						int nextPopulation = WORLD[nextY][nextX];
						int populationDiff = nextPopulation - currPopulation;
						if(populationDiff < 0) populationDiff *= -1;
						if(populationDiff >= L && populationDiff <= R) {
							move = true;
							mergeGroup.get(comp).add(nextXY);
							queue.add(nextXY);
							visit[nextY][nextX] = true;
						}
					}
				}
			}
		}
		
		for(int key : mergeGroup.keySet()) {
			List<int[]> merge = mergeGroup.get(key);
			int totalPopulation = 0;
			for(int[] country : merge) {
				int population = WORLD[country[1]][country[0]];
				totalPopulation += population;
			}
			int resultPopulation = totalPopulation / merge.size();
			for(int[] country : merge) {
				WORLD[country[1]][country[0]] = resultPopulation;
			}
		}
		
		return move;
	}
	
	int N;
	int L;
	int R;
	int[][] WORLD;
	public void solution() throws IOException {
		StringTokenizer inputNLR = new StringTokenizer(br.readLine());
		N = Integer.parseInt(inputNLR.nextToken());
		L = Integer.parseInt(inputNLR.nextToken());
		R = Integer.parseInt(inputNLR.nextToken());
		WORLD = new int[N][N];
		for(int y = 0 ; y < N ; y ++) {
			StringTokenizer lineInput = new StringTokenizer(br.readLine());
			for(int x = 0 ; x < N ; x ++) {
				int population = Integer.parseInt(lineInput.nextToken());
				WORLD[y][x] = population;
			}
		}
		
		int count = 0;
		while(true) {
			if(!bfs()) break;
			
			++ count;
		}
		
		bw.write(String.valueOf(count));
				
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
