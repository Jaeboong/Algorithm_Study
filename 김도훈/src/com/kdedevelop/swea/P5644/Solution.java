package com.kdedevelop.swea.P5644;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	int x;
	int y;
}

class Person {
	public Person(int x, int y, int number) {
		this.point = new Point(x, y);
		this.number = number;
	}
	
	static int[] dirX = {0, 1, 0, -1};
	static int[] dirY = {-1, 0, 1, 0};
	
	public void move(int dir) {
		if (dir == -1) return;
		this.point = new Point(point.x + dirX[dir], point.y + dirY[dir]);
	}
	
	Point point;
	int number;
	int charge = 0;
	public void charge(int charge) {
		this.charge += charge;
	}
	
	public String toString() {
		return "(NUMBER : " + number + " || X : " + point.x + " || Y : " + point.y + " CHARGE : " + charge + ")";
	}
}

class Charger extends Point {
	public Charger(int x, int y, int C, int P) {
		super(x, y);
		this.coverage = C;
		this.performance = P;
		this.connected = new HashSet<>();
	}
	int coverage;
	int performance;
	
	Set<Person> connected;
	public void clear() {
		connected.clear();
	}
	public void connect(Person person) {
		connected.add(person);
	}
	public String toString() {
		return "(X : " + x + " || Y : " + y + " || C : " + coverage + " || P : " + performance + ")";
	}
}

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int getDistance(Point a, Point b) {
		int distX = a.x - b.x;
		if (distX < 0) distX *= -1;
		int distY = a.y - b.y;
		if (distY < 0) distY *= -1;
		return distX + distY;
	}
	
	public void function() {
		Set<Charger>[] abailable = new Set[2];
		for (int i = 0 ; i < 2 ; i ++) abailable[i] = new HashSet<>();
		
		for (Charger charger : CHARGER) {
			charger.clear();
			for (Person person : PERSON) {
				if (getDistance(charger, person.point) <= charger.coverage) {
					abailable[person.number].add(charger);
				}
			}
		}
		
		int max = 0;
		int charge[] = new int[2];
		
		if (abailable[0].size() != 0 && abailable[1].size() != 0) {
			for (Charger aCharger : abailable[0]) {
				for (Charger bCharger : abailable[1]) {
					int aPerformance = aCharger.performance;
					int bPerformance = bCharger.performance;
					if (aCharger == bCharger) {
						aPerformance /= 2;
						bPerformance /= 2;
					}
					int sumPerformance = aPerformance + bPerformance;
					
					if (max < sumPerformance) { 
						max = sumPerformance;
						charge[0] = aPerformance;
						charge[1] = bPerformance;
					}
				}
			}
		} else if (abailable[0].size() == 0) {
			for (Charger bCharger : abailable[1]) {
				int bPerformance = bCharger.performance;
				int sumPerformance = bPerformance;
				if (max < sumPerformance) { 
					max = sumPerformance;
					charge[1] = bPerformance;
				}
			}
		} else if (abailable[1].size() == 0) {
			for (Charger aCharger : abailable[0]) {
				int aPerformance = aCharger.performance;
				int sumPerformance = aPerformance;
				if (max < sumPerformance) { 
					max = sumPerformance;
					charge[0] = aPerformance;
				}
			}
		}
		
//		for (Set<Charger> set : abailable) {
//			System.out.println(Arrays.toString(set.toArray()));
//		}
		
		for (int i = 0 ; i < 2 ; i ++) {
			PERSON[i].charge(charge[i]);
		}
	}
	
	int result;
	int M;
	int A;
	int[][] MOVE;
	Person[] PERSON;
	Charger[] CHARGER;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		
		for (int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			
			StringTokenizer inputMA = new StringTokenizer(br.readLine());
			M = Integer.parseInt(inputMA.nextToken());
			A = Integer.parseInt(inputMA.nextToken());
			MOVE = new int[2][M];
			for (int i = 0 ; i < 2 ; i ++) {
				StringTokenizer inputMove = new StringTokenizer(br.readLine());
				for (int j = 0 ; j < M ; j ++) {
					MOVE[i][j] = Integer.parseInt(inputMove.nextToken())-1;
				}
			}
			CHARGER = new Charger[A];
			for (int i = 0 ; i < A ; i ++) {
				StringTokenizer inputCharger = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(inputCharger.nextToken())-1;
				int y = Integer.parseInt(inputCharger.nextToken())-1;
				int c = Integer.parseInt(inputCharger.nextToken());
				int p = Integer.parseInt(inputCharger.nextToken());
				CHARGER[i] = new Charger(x, y, c, p);
			}
			
			PERSON = new Person[2];
			PERSON[0] = new Person(0, 0, 0);
			PERSON[1] = new Person(9, 9, 1);
			function();
			
			for (int i = 0 ; i < M ; i ++) {
//				System.out.println("\nMOVE : " + i);
				for (int j = 0 ; j < 2 ; j ++) {
//					System.out.println(MOVE[j][i]);
					PERSON[j].move(MOVE[j][i]);
				}
//				System.out.println(Arrays.toString(PERSON));
				function();
			}
			
			for (Person person : PERSON) {
				result += person.charge;
			}
			
			bw.write("#" + (testCase+1) + " " + result + "\n");
		}
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
