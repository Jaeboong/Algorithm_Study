package com.kdedevelop.swea.P7964;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	int result;
	int N;
	int D;
	boolean[] GATEs;
	public void solution() throws IOException {
		int TOTAL_TEST_CASE = Integer.parseInt(br.readLine());
		for(int testCase = 0 ; testCase < TOTAL_TEST_CASE ; testCase ++) {
			result = 0;
			StringTokenizer inputND = new StringTokenizer(br.readLine());
			N = Integer.parseInt(inputND.nextToken());
			D = Integer.parseInt(inputND.nextToken());
			GATEs = new boolean[N+2];
			GATEs[0] = true;
			GATEs[N + 1] = true;
			StringTokenizer inputGates = new StringTokenizer(br.readLine());
			for(int i = 1 ; i <= N ; i ++) {
				boolean gate = inputGates.nextToken().charAt(0) == '1';
				GATEs[i] = gate;
			}
			
			int current = 0;
			
			move:
			while(true) {
				if(current >= N+1) break;
				
				for(int dist = D ; dist > 0 ; dist --) {
//					System.out.println("D : " + dist);
					int next = current + dist;
					if(next > N+1) continue;
					
					boolean gate = GATEs[next];
					if(gate) {
						current = next;
						continue move;
					}
				}
				
				++ result;
				int next = current + D;
				current = next;
			}
			
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
