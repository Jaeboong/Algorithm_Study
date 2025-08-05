package com.kdedevelop.acmicpc.P22866;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Building {
	public Building(int height, int index) {
		this.height = height;
		this.index = index;
	}
	int height;
	int index;
	public String toString() {
		return "height : " + height + " || index : " + index;
	}
}

public class Main {
	
	public static int getDistance(int a, int b) {
		int distance = a - b;
		if (distance < 0) distance *= -1;
		return distance;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] building = new int[N];
		int[] result = new int[N];
		int[] near = new int[N];
		int[] distance = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i ++) {
			building[i] = Integer.parseInt(st.nextToken());
			distance[i] = Integer.MAX_VALUE;
		}
		
		Deque<Building> left = new ArrayDeque<>();
		Deque<Building> right = new ArrayDeque<>();
		
		left.add(new Building(building[N-1], N-1));
		right.add(new Building(building[0], 0));
		
		for (int i = N-2 ; i >= 0 ; i --) {
			int height = building[i];
			while (true) {
				if (left.size() <= 0) break;
				if (left.peek().height > height) break;
				left.pop();
			}
			//System.out.print("I : " + i + " || SIZE : " + left.size());
			result[i] += left.size();
			if (left.size() > 0) {
				int dis = getDistance(i, left.peek().index);
				//System.out.println(" |||| " + Arrays.toString(left.toArray()) + " |||| DISTANCE : " + dis);		
				near[i] = left.peek().index;
				distance[i] = dis;
			} else {
				//System.out.println("");
			}
			left.push(new Building(height, i));
		}
		
		//System.out.println("===");
		
		for (int i = 1 ; i < N ; i ++) {
			int height = building[i];
			while (true) {
				if (right.size() <= 0) break;
				if (right.peek().height > height) break;
				right.pop();
			}
			//System.out.print("I : " + i + " || SIZE : " + right.size());
			result[i] += right.size();
			if (right.size() != 0) {
				int dis = getDistance(i, right.peek().index);
				//System.out.println(" |||| " + Arrays.toString(right.toArray()) + " |||| DISTANCE : " + dis);				
				if (distance[i] >= dis) {
					near[i] = right.peek().index;
				}
			} else {
				//System.out.println("");
			}
			right.push(new Building(height, i));
		}
		
		//System.out.println("---");
		
		for (int i = 0 ; i < N ; i ++) bw.write(String.valueOf(result[i]) + (result[i] != 0 ? " " + (near[i] + 1) : "") + "\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}
