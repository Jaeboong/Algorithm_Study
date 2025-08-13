package com.kdedevelop.acmicpc.P14888;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw;
	static long minResult = Integer.MAX_VALUE;
	static long maxResult = Integer.MIN_VALUE;
	
	public static char getOperator(int[] operatorSumCounter, int operatorNumber) {
		for (int i = 0 ; i < 4 ; i ++) {
			if (operatorSumCounter[i] <= operatorNumber && operatorSumCounter[i+1] > operatorNumber) {
				switch (i) {
					case 0 : return '+';
					case 1 : return '-';
					case 2 : return '*';
					case 3 : return '/';
				}
			}
		}
		throw new RuntimeException("something wrong");
	}
	
	public static long calc(int[] A, int[] operatorSumCounter, int[] operators) {
		Deque<Character> operatorStack = new ArrayDeque<>();
		Deque<Long> operandStack = new ArrayDeque<>();
		
		for (int i = 0 ; i < A.length ; i ++) operandStack.addLast((long) A[i]);
		for (int i = 0 ; i < operators.length ; i ++) operatorStack.addLast(getOperator(operatorSumCounter, operators[i]));
//		for (int i = A.length-1 ; i >= 0 ; i --) operandStack.add((long) A[i]);
//		for (int i = operators.length-1 ; i >= 0 ; i --) operatorStack.add(getOperator(operatorSumCounter, operators[i]));
		
//		System.out.println(Arrays.toString(operatorStack.toArray()));
//		System.out.println(Arrays.toString(operandStack.toArray()));
		
		while (true) {
			if (operatorStack.isEmpty()) break;
			
			long a = operandStack.pop();
			long b = operandStack.pop();
			char operator = operatorStack.pop();
			
//			System.out.println(a + " " + operator + " " + b);
			
			long result;
			
			switch (operator) {
				case '+' : result = a + b;
					break;
				case '-' : result = a - b;
					break;
				case '*' : result = a * b;
					break;
				case '/' : result = a / b;
					break;
				default : throw new RuntimeException("something wrong");
			}
			
			operandStack.addFirst(result);
		}
		
		return operandStack.pop();
	}
	
	public static void dfs(int n, int r, int[] A, int[] operatorSumCounter, int depth, boolean[] visited, int[] operators) {
		if (depth == r) {
			long result = calc(A, operatorSumCounter, operators);
			minResult = Math.min(minResult, result);
			maxResult = Math.max(maxResult, result);
		} else {
			for (int i = 0 ; i < n ; i ++) {
				if (visited[i]) continue;
				visited[i] = true;
				operators[depth] = i;
				dfs(n, r, A, operatorSumCounter, depth+1, visited, operators);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer inputN = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i ++) A[i] = Integer.parseInt(inputN.nextToken());
		int[] operatorCount = new int[4]; //0=+, 1=-, 2=*, 3=/
		int[] operatorSumCount = new int[5];
		StringTokenizer inputOperator = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < 4 ; i ++) {
			operatorCount[i] = Integer.parseInt(inputOperator.nextToken());
			operatorSumCount[i+1] = operatorSumCount[i] + operatorCount[i];
		}
		
		dfs(N-1, N-1, A, operatorSumCount, 0, new boolean[N-1], new int[N-1]);
		
		bw.write(String.valueOf(maxResult) + "\n" + String.valueOf(minResult));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
