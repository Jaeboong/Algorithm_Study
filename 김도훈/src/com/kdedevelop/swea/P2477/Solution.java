package com.kdedevelop.swea.P2477;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Info implements Comparable<Info> {
	public Info(int customer, int time) {
		this.number = customer;
		this.oper = Oper.ENTER_DESK;
		this.time = time;
		this.desk = -1;
		this.engineer = -1;
		this.wait = 0;
	}
	
	int number;
	Oper oper;
	int time;
	int desk;
	int engineer;
	int wait;
	
	@Override
	public int compareTo(Info o) {
		int timeCompare = Integer.compare(this.time, o.time);
		
		if(timeCompare == 0) {
			
			int operCompare = Integer.compare(this.oper.ordinal(), o.oper.ordinal());
			
			if(operCompare == 0) {
				if(oper == Oper.ENTER_DESK) {
					int numberCompare = Integer.compare(this.number, o.number);
					return numberCompare;
				}
				
				if(oper == Oper.ENTER_ENGINEER) {
					int waitComapre = Integer.compare(this.wait, o.wait) * -1;
					
					if(waitComapre == 0) {
						int deskCompare = Integer.compare(this.desk, o.desk);
						return deskCompare;
					}
					
					return waitComapre;
				}
			}
			
			return operCompare;
			
		}
		
		return timeCompare;
	}
}
	
enum Oper {
	END_ENGINEER, END_DESK, ENTER_ENGINEER, ENTER_DESK;
}

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public String opperToString(Oper oper) {
		switch(oper) {
			case ENTER_DESK : return "enter desk    ";
			case END_DESK : return "end   desk    ";
			case ENTER_ENGINEER : return "enter engineer";
			case END_ENGINEER : return "end   engineer";
		}
		return "-1";
	}
	
	public String toString(Info info) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(info.number).append(" IS ").append(opperToString(info.oper)).append(" IN TIME : ").append(String.format("%2d", info.time)).append(" WITH DESK : ").append(info.desk).append(" WITH ENGINEER : ").append(info.engineer).append(" WAIT IN : ").append(info.wait);
		
		return sb.toString();
	}
	
	public void function() {
		while(true) {
			if(queue.isEmpty()) break;
			
			Info customer = queue.poll();
			Oper opper = customer.oper;
			int number = customer.number;
			
//			System.out.println("==========================================");
//			System.out.println("BRFORE : " + toString(info));
			
			switch(opper) {
				case ENTER_DESK : {
					if(deskQueue.isEmpty()) {
						++ customer.time;
						++ customer.wait;
						queue.offer(customer);
					} else {
						int deskIndex = deskQueue.poll();
						int deskTime = AI[deskIndex];
						customer.oper = Oper.END_DESK;
						customer.time += deskTime;
						customer.desk = deskIndex;
						customer.wait = 0;
						queue.offer(customer);
					}
					break;
				}
				case END_DESK : {
					int deskIndex = customer.desk;
					deskQueue.offer(deskIndex);
					customer.oper = Oper.ENTER_ENGINEER;
					queue.offer(customer);
					break;
				}
				case ENTER_ENGINEER : {
					if(engineerQueue.isEmpty()) {
						++ customer.time;
						++ customer.wait;
						queue.offer(customer);
					} else {
						int engineerIndex = engineerQueue.poll();
						int engineerTime = BJ[engineerIndex];
						customer.oper = Oper.END_ENGINEER;
						customer.time += engineerTime;
						customer.engineer = engineerIndex;
						customer.wait = 0;
						queue.offer(customer);
					}
					break;
				}
				case END_ENGINEER : {
					int engineerIndex = customer.engineer;
					engineerQueue.offer(engineerIndex);
					break;
				}
			}
			
//			System.out.println("AFTER  : " + toString(info));
//			System.out.println("==========================================");
		}
		
	}
	
	long result;
	int N;
	int M;
	int K;
	int A;
	int B;
	int bDeskIndex;
	int bEngineerIndex;
	int[] AI;
	int[] BJ;
	int[] TK;
	Info[] CUSTOMERs;
	Queue<Info> queue;
	Queue<Integer> deskQueue;
	Queue<Integer> engineerQueue;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = 0;
			StringTokenizer inputNMKAB = new StringTokenizer(br.readLine().trim());
			queue = new PriorityQueue<>();
			deskQueue = new PriorityQueue<>();
			engineerQueue = new PriorityQueue<>();
			N = Integer.parseInt(inputNMKAB.nextToken());
			M = Integer.parseInt(inputNMKAB.nextToken());
			K = Integer.parseInt(inputNMKAB.nextToken());
			A = Integer.parseInt(inputNMKAB.nextToken()) - 1;
			B = Integer.parseInt(inputNMKAB.nextToken()) - 1;
			AI = new int[N];
			StringTokenizer inputAI = new StringTokenizer(br.readLine().trim());
			for(int i = 0 ; i < N ; i ++) {
				deskQueue.offer(i);
				int value = Integer.parseInt(inputAI.nextToken());
				AI[i] = value;
			}
			BJ = new int[M];
			StringTokenizer inputBJ = new StringTokenizer(br.readLine().trim());
			for(int i = 0 ; i < M ; i ++) {
				engineerQueue.offer(i);
				int value = Integer.parseInt(inputBJ.nextToken());
				BJ[i] = value;
			}
			CUSTOMERs = new Info[K];
			TK = new int[K];
			StringTokenizer inputTK = new StringTokenizer(br.readLine().trim());
			for(int i = 0 ; i < K ; i ++) {
				int value = Integer.parseInt(inputTK.nextToken());
				TK[i] = value;
				
				Info customer = new Info(i, value);
				CUSTOMERs[i] = customer;
				queue.offer(customer);
			}
			
			function();
			
			for(Info customer : CUSTOMERs) {
//				System.out.println(Arrays.toString(customer));
				if(customer.desk == A && customer.engineer == B) {
					result += (customer.number + 1);
				}
			}
			
			bw.write("#" + (testCase + 1) + " " + (result == 0 ? -1 : result) + "\n");

		}
					
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}
}
