package com.kdedevelop.acmicpc.P1654;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int K;
	int N;
	long[] CABLEs;
	public void solution() throws IOException {
		StringTokenizer inputKN = new StringTokenizer(br.readLine());
		K = Integer.parseInt(inputKN.nextToken());
		N = Integer.parseInt(inputKN.nextToken());
		CABLEs = new long[K];
		long max = Long.MIN_VALUE;
		for(int i = 0 ; i < K ; i ++) {
			long cable = Long.parseLong(br.readLine());
			if (max < cable) max = cable;
			CABLEs[i] = cable;
		}
		
//		Arrays.sort(CABLEs);
		
		long start = 1;
		long end = max;
		long result = 0;
		while(true) {
			if(start > end) break;
			
			long middle = (start + end) / 2;
			
			long cableCount = 0;
			for(long cable : CABLEs) cableCount += cable / middle;
//			System.out.xprintln("START : " + start + " || END : " + end + " || MIDDLE : " + middle + " || CABLE COUNT : " + cableCount);
			
			if(cableCount < N) {
				end = middle - 1;
			} 
			
			if(cableCount >= N) {
				result = middle;
				start = middle + 1;
			}
		}
		
		bw.write(String.valueOf(result));
				
		br.close();
		bw.flush();
		bw.close();
	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}
