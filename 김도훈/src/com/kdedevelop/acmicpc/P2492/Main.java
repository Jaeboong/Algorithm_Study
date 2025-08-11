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
		Set<Integer> xCan = new HashSet<>();
		Set<Integer> yCan = new HashSet<>();
		for (int i = 0 ; i < T ; i ++) {
			Point intruder = new Point(br.readLine());
			xCan.add(intruder.x);
			yCan.add(intruder.y);
			intruders[i] = intruder;
		}
		
		int resultCounter = 0;
		Point resultPoint = new Point(0, 0);
		for (int xCa : xCan) {
			for (int yCa : yCan) {
				int x = xCa;
				int y = yCa;
				if (x > N - K) x = N - K;
				if (y > M - K) y = M - K;
				
				int counter = 0;
				for (Point intruder : intruders) {
					if (intruder.x < x || intruder.x > x + K) continue;
					if (intruder.y < y || intruder.y > y + K) continue;
					counter ++;
				}
				
				if (counter > resultCounter) {
					resultCounter = counter;
					resultPoint.x = x;
					resultPoint.y = y;
				}
			}
		}
		
		bw.write(resultPoint.x + " " + (resultPoint.y+K) + "\n");
		bw.write(resultCounter + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}

