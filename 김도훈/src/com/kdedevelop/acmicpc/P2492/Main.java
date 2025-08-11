package com.kdedevelop.acmicpc.P2492;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


class Point {
	public Point(String input) {
		StringTokenizer st = new StringTokenizer(input);
		this.x = Integer.parseInt(st.nextToken());
		this.y = Integer.parseInt(st.nextToken());
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	int x;
	int y;
}

public class Main {
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("Test4.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNMTK = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNMTK.nextToken());
		int M = Integer.parseInt(inputNMTK.nextToken());
		int T = Integer.parseInt(inputNMTK.nextToken());
		int K = Integer.parseInt(inputNMTK.nextToken());
		
		Point[] intruders = new Point[T];
		Set<Integer> xList = new HashSet<>();
		Set<Integer> yList = new HashSet<>();
		for (int i = 0 ; i < T ; i ++) {
			Point intruder = new Point(br.readLine());
			xList.add(intruder.x);
			yList.add(intruder.y);
			intruders[i] = intruder;
		}
		
		int resultCounter = 0;
		Point resultPoint = new Point();
		
		for (int xElement : xList) {
			for (int yElement : yList) {
				int x = Math.min(xElement, N-K);
				int y = Math.min(yElement, M-K);
				
				int counter = 0;
				for (Point intruder : intruders) {
					int xDiff = intruder.x - x;
					int yDiff = intruder.y - y;
					
					if (xDiff < 0 || yDiff < 0) continue;
					if (xDiff > K || yDiff > K) continue;
					
					counter ++;
				}
				
				if (counter > resultCounter) {
					resultCounter = counter;
					resultPoint.x = x;
					resultPoint.y = y;
				}
			}
		}
		
		bw.write(resultPoint.x + " " + (resultPoint.y+K) + "\n" + resultCounter + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}

