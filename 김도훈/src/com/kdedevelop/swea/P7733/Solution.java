package com.kdedevelop.swea.P7733;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int[] dirX = {-1, 1, 0, 0};
	int[] dirY = {0, 0, -1, 1};
	
	public boolean isOutOfCheese(Point point) {
		if (point.x < 0 || point.x >= N) return true;
		if (point.y < 0 || point.y >= N) return true;
		return false;
	}
	
	int N;
	int[][] CHEESE;
	int result;
	Map<Integer, List<Point>> cheeseMap;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
//			System.out.println("TESTCASE : " + testCase);
			result = 0;
			N = Integer.parseInt(br.readLine());
			CHEESE = new int[N][N];
			cheeseMap = new TreeMap<>();
			
			int maxTasty = 0;
			
			for (int i = 0 ; i < N ; i ++) {
				StringTokenizer inputLine = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < N ; j ++) {
					int tasty = Integer.parseInt(inputLine.nextToken());
					maxTasty = Math.max(maxTasty, tasty);
					CHEESE[i][j] = tasty;
					if (!cheeseMap.containsKey(tasty)) cheeseMap.put(tasty, new LinkedList<>());
					cheeseMap.get(tasty).add(new Point(j, i));
				}
			}
			
			for (int day = 0 ; day <= maxTasty ; day ++) {
				Queue<Point> queue = new LinkedList<>();
				int count = 0;
				boolean[][] visited = new boolean[N][N];
				for (int i = 0 ; i < N ; i ++) {
					for (int j = 0 ; j < N ; j ++) {
						if (CHEESE[i][j] <= day) continue;
						if (visited[i][j]) continue;
						
//						System.out.println("DAY : " + day + " || I : " + i + " || J : " + j);
						
						queue.add(new Point(j, i));
						count ++;
						while (true) {
							if (queue.isEmpty()) break;
							
							Point curr = queue.poll();
//							System.out.println("X : " + curr.x + " || Y : " + curr.y);
							if (visited[curr.y][curr.x]) continue;
							visited[curr.y][curr.x] = true;
							
							for (int dir = 0 ; dir < 4 ; dir ++) {
								Point nextPoint = new Point(curr.x + dirX[dir], curr.y + dirY[dir]);
								
								if (isOutOfCheese(nextPoint)) continue;
								if (visited[nextPoint.y][nextPoint.x]) continue;
								if (CHEESE[nextPoint.y][nextPoint.x] <= day) continue;
								
								queue.add(nextPoint);
							}
						}
					}
//					System.out.println("DAY : " + day + " || COUNT : " + count);
				}
				result = Math.max(result, count);
			}
			
		
			bw.write("#" + (testCase + 1) + " " + result + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
