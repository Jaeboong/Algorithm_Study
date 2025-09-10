package com.kdedevelop.acmicpc.P1463;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public int function(int number) {
		if(memo[number] != 0) {
			return memo[number];
		} 
		
		int result = Integer.MAX_VALUE;
		if(number % 2 == 0) {
			 result = Math.min(function(number / 2) + 1, result);
		}
		
		if(number % 3 == 0) {
			result = Math.min(function(number / 3) + 1, result);
		}
		
		result = Math.min(function(number - 1) + 1, result);
		
		memo[number] = result;
		
//		System.out.println("NUMBER : " + number + " || RESULT : " + result);
		
		return result;
	}
	
	public void function2() {
		for(int i = 2 ; i <= N ; i ++) {
//			System.out.println("I : " + i);
			
			memo[i] = Math.min(memo[i], memo[i - 1] + 1);
			
			for(int j = i, count = memo[i] ; j <= N ; j *= 2, count ++) {
//				System.out.println("J : " + j);
				memo[j] = Math.min(memo[j], count);
//				if(memo[j] < count) break;
//				memo[j] = count;
			}
			for(int j = i, count = memo[i] ; j <= N ; j *= 3, count ++) {
//				System.out.println("J : " + j);
				memo[j] = Math.min(memo[j], count);
//				if(memo[j] < count) break;
//				memo[j] = count;
			}
		}
	}
	
	void print() {
		for(int i = 0 ; i <= N ; i ++) {
			System.out.print(String.format("%3d", i));
		}
		System.out.println("");
		for(int i = 0 ; i <= N ; i ++) {
			System.out.print(String.format("%3d", (memo[i] == Integer.MAX_VALUE ? -1 : memo[i])));
		}
	}
	
	int N;
	int result;
	int[] memo;
	public void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		memo = new int[Math.max(4, N + 1)];
		Arrays.fill(memo, Integer.MAX_VALUE);
		
		memo[0] = 0;
		memo[1] = 0;
		memo[2] = 1;
		memo[3] = 1;
		
//		result = function(N);
		function2();
//		print();
		
		result = memo[N];
		
//		System.out.println(Arrays.toString(memo));
		
		bw.write(String.valueOf(result));
		
		br.close();
		bw.flush();
		bw.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
