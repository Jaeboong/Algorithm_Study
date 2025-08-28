package com.kdedevelop.swea.P2383;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public int getDistance(int[] a, int[] b) {
		int distX = a[0] - b[0];
		if(distX < 0) distX *= -1;
		int distY = a[1] - b[1];
		if(distY < 0) distY *= -1;
		return distX + distY;
	}
	
	public boolean test(boolean[] test) {
		if(test[0] == true) return false;
		if(test[1] == true) return false;
		if(test[2] == true) return false;
		if(test[3] == true) return false;
		if(test[4] == false) return false;
		if(test[5] == false) return false;
		return true;
	}
	
	public int move(boolean[] data) {
		int min = 0;
		
	
		//OPER : 1 -> STAIR ARRIVED || 0 -> START STAIR || 2 -> END STAIR
		//0 -> TIME, 1 -> OPER || 2 -> STAIR NUMBER || 3 -> PERSON INDEX
		Queue<int[]> order = new PriorityQueue<>((e1, e2) -> {
			int timeOrder = Integer.compare(e1[0], e2[0]);
			if(timeOrder == 0) {
				int operOrder = Integer.compare(e2[1], e1[1]);
				if(operOrder == 0) {
					int personOrder = Integer.compare(e1[3], e2[3]);
					return personOrder;
				} else {
					return operOrder;
				}
			} else {
				return timeOrder;
			}
		});
		
		List<int[]> personList = new ArrayList<>(PERSONs.size());
		for(int[] person : PERSONs) personList.add(Arrays.copyOf(person, 2));
		
		for(int i = 0 ; i < personList.size() ; i ++) {
			int[] person = personList.get(i);
			int stairIndex = data[i] ? 1 : 0;
			int[] stair = STAIRs.get(stairIndex);
			
			int dist = getDistance(stair, person);
//			if(test(data)) System.out.println("PERSON : " + i + " || TIME : " + dist);
			
			order.offer(new int[] {dist, 1, stairIndex, i});
		}
		
		int[] stairState = new int[2];
		while(true) {
			if(order.isEmpty()) break;
			
			int[] curr = order.poll();
			int oper = curr[1];
			int stairIndex = curr[2];
			
			int[] stairPosition = STAIRs.get(stairIndex);
			int stairSpendTime = MAP[stairPosition[1]][stairPosition[0]];
			
//			if(test(data)) System.out.print("CURR -> TIME : " + curr[0] + " || PERSON : " + curr[3] + " || STAIR NUMBER : " + stairIndex + " ||");
			
			if(oper == 1) {
//				if(test(data)) System.out.print(" ARRIVED");
				++ curr[0];
				curr[1] = 0;
				
				order.offer(curr);
			}
			if(oper == 0) {
//				if(test(data)) System.out.print(" START STAIR");
				if(stairState[stairIndex] < 3) {
					curr[0] += stairSpendTime;
					curr[1] = 2;
					++ stairState[stairIndex];
				} else {
//					if(test(data)) System.out.print(" BUT STIAR IS FULL.");
					curr[0] ++;
				}
				
				order.offer(curr);
			}
			if(oper == 2) {
//				if(test(data)) System.out.print(" END STAIR");
				-- stairState[stairIndex];
			}
			
//			if(test(data)) System.out.println(" || " + Arrays.toString(stairState));
			
			min = curr[0];
		}
		
		return min;
	}
	
	public void dfs(int depth, boolean[] data) {
		if(depth == PERSONs.size()) {
			int minuts = move(data);
			if(result > minuts) {
//				System.out.println(Arrays.toString(data) + " : " + minuts);
				result = minuts;
			}
		} else {
			data[depth] = true;
			dfs(depth + 1, data);
			data[depth] = false;
			dfs(depth + 1, data);
		}
	}
	
	long result;
	int N;
	List<int[]> PERSONs;
	List<int[]> STAIRs;
	int[][] MAP;
	public void solution() throws IOException {
		int TotalTestCase = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TotalTestCase ; testCase ++) {
			result = Integer.MAX_VALUE;
			
			N = Integer.parseInt(br.readLine());
			PERSONs = new ArrayList<>(10);
			STAIRs = new ArrayList<>(2);
			MAP = new int[N][N];
			for(int y = 0 ; y < N ; y ++) {
				StringTokenizer lineInput = new StringTokenizer(br.readLine());
				for(int x = 0 ; x < N ; x ++) {
					int value = Integer.parseInt(lineInput.nextToken());
					if(value == 1) PERSONs.add(new int[] {x, y});
					if(value >= 2) STAIRs.add(new int[] {x, y});
					MAP[y][x] = value;
				}
			}
			
			dfs(0, new boolean[PERSONs.size()]);
			
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
