package com.kdedevelop.acmicpc.P2559;

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
		
		StringTokenizer inputNK = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(inputNK.nextToken());
		int K = Integer.parseInt(inputNK.nextToken());
		
		int[] temperature = new int[N];
		StringTokenizer inputTemperature = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < N ; i ++) {
			temperature[i] = Integer.parseInt(inputTemperature.nextToken());
		}
		
		int[] sumOfTemperature = new int[N-K+1];
		for (int i = 0 ; i < K ; i ++) {
			sumOfTemperature[0] += temperature[i];
		}
		int max = sumOfTemperature[0];
		for (int i = 1 ; i <= N-K ; i ++) {
			sumOfTemperature[i] = sumOfTemperature[i-1] - temperature[i-1] + temperature[i-1+K];
			max = Math.max(max, sumOfTemperature[i]);
		}
		
//		for (int sum : sumOfTemperature) {
//			System.out.println(sum);
//		}
		
		bw.write(String.valueOf(max));
		
		bw.flush();
		bw.close();
		br.close();
	}

}
