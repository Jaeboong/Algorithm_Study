package com.kdedevelop.acmicpc.P10986;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer inputNM = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(inputNM.nextToken());
		int M = Integer.parseInt(inputNM.nextToken());
		
		int[] num = new int[N];
		long[] sum = new long[N+1];
		int[] module = new int[M];
		
		StringTokenizer input = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i < N ; i ++) {
			int inputNumber = Integer.parseInt(input.nextToken());
			num[i] = inputNumber;
			sum[i+1] = (long) sum[i] + (long) inputNumber;
			module[(int) (sum[i+1] % M)] ++;
		}
		
//		for (int temp : sum) {
//			System.out.println(temp);
//		}
		
//		for (int temp : module) {
//			System.out.println(temp);
//		}
		
		long result = module[0];
		
		for (int i = 0 ; i < M ; i ++) {
			int n = module[i];
			int r = 2;
			
			result += (long) (n * (long) (n-1)) / (long) r;
		}
		
//		for (int i = 1 ; i <= N ; i ++) {
//			for (int j = 0 ; j < i ; j ++) {
//				if (i == j) continue;
//				
//				int diff = sum[i] - sum[j];
//				
////				System.out.println(sum[i] + "-" + sum[j] + "=" + diff);
//				
//				if (diff % M == 0) result ++;
//			}
//		}
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
