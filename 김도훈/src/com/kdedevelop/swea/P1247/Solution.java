package com.kdedevelop.swea.P1247;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point(String x, String y) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
	}
	int x;
	int y;
	public String toString() {
		return "X : " + x + " || Y : " + y;
	}
}
public class Solution {
	public static int getDistance(Point a, Point b) {
		int xDistance = a.x - b.x;
		if (xDistance < 0) xDistance *= -1;
		int yDistance = a.y - b.y;
		if (yDistance < 0) yDistance *= -1;
		return xDistance + yDistance;
	}
	
	static int result;
	public static void dfs(Point company, Point house, Point[] customers, int numberOfCustomers, int depth, boolean[] visited, int[] data) {
		if (depth == numberOfCustomers) {
			int sum = 0;
			sum += getDistance(company, customers[data[0]]);
			for (int i = 0 ; i < numberOfCustomers-1 ; i ++) sum += getDistance(customers[data[i]], customers[data[i+1]]);
			sum += getDistance(customers[data[numberOfCustomers-1]], house);
			result = Math.min(result, sum);
		} else {
			for (int i = 0 ; i < numberOfCustomers ; i ++) {
				if (visited[i]) continue;
				visited[i] = true;
				data[depth] = i;
				dfs(company, house, customers, numberOfCustomers, depth+1, visited, data);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:\\Users\\SSAFY\\Downloads\\input (3).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Integer.MAX_VALUE;
			int numberOfCustomer = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Point company = new Point(st.nextToken(), st.nextToken());
			Point house = new Point(st.nextToken(), st.nextToken());
			
			Point[] customers = new Point[numberOfCustomer];
			for (int i = 0 ; i < numberOfCustomer ; i ++) customers[i] = new Point(st.nextToken(), st.nextToken());
			
			dfs(company, house, customers, numberOfCustomer, 0, new boolean[numberOfCustomer], new int[numberOfCustomer]);
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
